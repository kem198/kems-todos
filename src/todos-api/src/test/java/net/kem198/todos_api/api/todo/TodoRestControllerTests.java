package net.kem198.todos_api.api.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.test.web.client.TestRestTemplate;
import net.kem198.todos_api.domain.repository.todo.TodoRepository;
import net.kem198.todos_api.domain.model.Todo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoRestControllerTests {

    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private TodoRepository todoRepository;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        // テストデータをクリア
        todoRepository.findAll().forEach(todo -> todoRepository.delete(todo));
    }

    @Nested
    class GetTodosTests {
        @Test
        @DisplayName("Todo 一覧と 200 を返すこと")
        void returnsTodoListWith200() throws Exception {
            // Arrange
            createTodo("Test Todo 1", "Description 1");
            createTodo("Test Todo 2", "Description 2");

            // Act
            ResponseEntity<String> response = restTemplate.getForEntity("/v1/todos", String.class);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertTrue(responseBody.isArray());
            assertEquals(2, responseBody.size());
        }
    }

    @Nested
    class GetTodoTests {
        @Test
        @DisplayName("指定された ID の Todo と 200 を返すこと")
        void returnsTodoByIdWith200() throws Exception {
            // Arrange
            String todoId = createTodo("Test Todo", "Test Description");

            // Act
            ResponseEntity<String> response = restTemplate.getForEntity("/v1/todos/" + todoId, String.class);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertEquals("Test Todo", responseBody.get("todoTitle").asText());
            assertEquals("Test Description", responseBody.get("todoDescription").asText());
        }

        @Test
        @DisplayName("存在しない ID の場合は 404 を返すこと")
        void returnsNotFoundWith404ForNonExistentId() throws Exception {
            // Act
            ResponseEntity<String> response = restTemplate.getForEntity("/v1/todos/nonexistent", String.class);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }

    @Nested
    class PostTodoTests {
        @Test
        @DisplayName("Todo がデータベースに保存されること")
        void savesTodoToDatabase() throws Exception {
            // Arrange
            String requestBody = """
                        {
                            "todoTitle": "Hello World!",
                            "todoDescription": "Hello Todo Description!"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/v1/todos", requestEntity, String.class);
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            String createdTodoId = responseBody.get("todoId").asText();

            // Assert
            var savedTodo = todoRepository.findById(createdTodoId);
            assertNotNull(savedTodo);
            assertEquals("Hello World!", savedTodo.getTodoTitle());
            assertEquals("Hello Todo Description!", savedTodo.getTodoDescription());
            assertFalse(savedTodo.isFinished());
            assertNotNull(savedTodo.getCreatedAt());
        }

        @Test
        @DisplayName("作成された Todo と 201 を返すこと")
        void returnsCreatedTodoWith201() throws Exception {
            // Arrange
            String requestBody = """
                        {
                            "todoTitle": "Hello World!",
                            "todoDescription": "Hello Todo Description!"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/v1/todos", requestEntity, String.class);

            // Assert
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertEquals("Hello World!", responseBody.get("todoTitle").asText());
            assertEquals("Hello Todo Description!", responseBody.get("todoDescription").asText());
            assertEquals(false, responseBody.get("finished").asBoolean());
            assertNotNull(responseBody.get("todoId").asText());
            assertNotNull(responseBody.get("createdAt").asText());
        }

        @Test
        @DisplayName("未完了 Todo が上限に達している場合は 400 を返すこと")
        void returnsBadRequestWith400WhenUnfinishedTodoLimitReached() throws Exception {
            // Arrange - 未完了 Todo を上限まで作成（5個）
            for (int i = 0; i < 5; i++) {
                createTodo("Todo " + i, "Description " + i);
            }

            String requestBody = """
                        {
                            "todoTitle": "Overflow Todo",
                            "todoDescription": "This should fail"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/v1/todos", requestEntity, String.class);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertTrue(responseBody.get("detail").asText().contains("The count of un-finished Todo must not be over"));
        }

        @Test
        @DisplayName("未完了 Todo が上限に達している場合は新しい Todo が作成されないこと")
        void doesNotCreateTodoWhenUnfinishedTodoLimitReached() throws Exception {
            // Arrange - 未完了 Todo を上限まで作成（5個）
            for (int i = 0; i < 5; i++) {
                createTodo("Todo " + i, "Description " + i);
            }

            String requestBody = """
                        {
                            "todoTitle": "Overflow Todo",
                            "todoDescription": "This should fail"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            restTemplate.postForEntity("/v1/todos", requestEntity, String.class);

            // Assert
            var allTodos = todoRepository.findAll();
            assertEquals(5, allTodos.size()); // 上限の5個のままで増えていない
            assertTrue(allTodos.stream().noneMatch(todo -> "Overflow Todo".equals(todo.getTodoTitle())));
        }

        @Test
        @DisplayName("バリデーションエラーの場合は 400 を返すこと")
        void returnsBadRequestWith400ForValidationError() throws Exception {
            // Arrange
            String requestBody = """
                        {
                            "todoTitle": "",
                            "todoDescription": "Description"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            ResponseEntity<String> response = restTemplate.postForEntity("/v1/todos", requestEntity, String.class);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("バリデーションエラーの場合は新しい Todo が作成されないこと")
        void doesNotCreateTodoForValidationError() throws Exception {
            // Arrange - 事前の Todo 数を確認
            var initialTodos = todoRepository.findAll();
            int initialCount = initialTodos.size();

            String requestBody = """
                        {
                            "todoTitle": "",
                            "todoDescription": "Description"
                        }
                    """;

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

            // Act
            restTemplate.postForEntity("/v1/todos", requestEntity, String.class);

            // Assert
            var todosAfter = todoRepository.findAll();
            assertEquals(initialCount, todosAfter.size());
        }
    }

    @Nested
    class PutTodoFinishTests {
        @Test
        @DisplayName("Todo がデータベースで完了状態に更新されること")
        void updatesTodoToFinishedInDatabase() throws Exception {
            // Arrange
            String todoId = createTodo("Test Todo", "Test Description");

            // Act
            restTemplate.exchange(
                    "/v1/todos/" + todoId,
                    HttpMethod.PUT,
                    null,
                    String.class);

            // Assert
            var updatedTodo = todoRepository.findById(todoId);
            assertNotNull(updatedTodo);
            assertTrue(updatedTodo.isFinished());
            assertEquals("Test Todo", updatedTodo.getTodoTitle());
            assertEquals("Test Description", updatedTodo.getTodoDescription());
        }

        @Test
        @DisplayName("完了状態の Todo と 200 を返すこと")
        void returnsFinishedTodoWith200() throws Exception {
            // Arrange
            String todoId = createTodo("Test Todo", "Test Description");

            // Act
            ResponseEntity<String> response = restTemplate.exchange(
                    "/v1/todos/" + todoId,
                    HttpMethod.PUT,
                    null,
                    String.class);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertEquals(true, responseBody.get("finished").asBoolean());
        }

        @Test
        @DisplayName("既に完了済みの Todo の場合は 400 を返すこと")
        void returnsBadRequestWith400ForAlreadyFinishedTodo() throws Exception {
            // Arrange
            String todoId = createTodo("Test Todo", "Test Description");
            // 一度完了させる
            restTemplate.exchange("/v1/todos/" + todoId, HttpMethod.PUT, null, String.class);

            // Act - 再度完了させようとする
            ResponseEntity<String> response = restTemplate.exchange(
                    "/v1/todos/" + todoId,
                    HttpMethod.PUT,
                    null,
                    String.class);

            // Assert
            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertTrue(responseBody.get("detail").asText().contains("The Todo is already finished"));
        }

        @Test
        @DisplayName("既に完了済みの Todo の場合はデータベースの状態が変わらないこと")
        void doesNotChangeDatabaseStateForAlreadyFinishedTodo() throws Exception {
            // Arrange
            String todoId = createTodo("Test Todo", "Test Description");
            // 一度完了させる
            restTemplate.exchange("/v1/todos/" + todoId, HttpMethod.PUT, null, String.class);

            // Act - 再度完了させようとする
            restTemplate.exchange("/v1/todos/" + todoId, HttpMethod.PUT, null, String.class);

            // Assert
            var todo = todoRepository.findById(todoId);
            assertNotNull(todo);
            assertTrue(todo.isFinished()); // 完了状態のままであることを確認
        }

        @Test
        @DisplayName("存在しない Todo の場合は 404 を返すこと")
        void returnsNotFoundWith404ForNonExistentTodo() throws Exception {
            // Act
            ResponseEntity<String> response = restTemplate.exchange(
                    "/v1/todos/nonexistent",
                    HttpMethod.PUT,
                    null,
                    String.class);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
            JsonNode responseBody = objectMapper.readTree(response.getBody());
            assertTrue(responseBody.get("detail").asText().contains("Resource is not found"));
        }
    }

    @Nested
    class DeleteTodoTests {
        @Test
        @DisplayName("Todo がデータベースから削除されること")
        void deletesTodoFromDatabase() throws Exception {
            // Arrange
            String todoId = createTodo("Test Todo", "Test Description");

            // Act
            restTemplate.exchange(
                    "/v1/todos/" + todoId,
                    HttpMethod.DELETE,
                    null,
                    String.class);

            // Assert
            var deletedTodo = todoRepository.findById(todoId);
            assertNull(deletedTodo);
        }

        @Test
        @DisplayName("Todo が削除され 204 を返すこと")
        void deletesTodoAndReturns204() throws Exception {
            // Arrange
            String todoId = createTodo("Test Todo", "Test Description");

            // Act
            ResponseEntity<String> response = restTemplate.exchange(
                    "/v1/todos/" + todoId,
                    HttpMethod.DELETE,
                    null,
                    String.class);

            // Assert
            assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

            // 削除後、取得しようとすると 404 になることを確認
            ResponseEntity<String> getResponse = restTemplate.getForEntity("/v1/todos/" + todoId, String.class);
            assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
        }

        @Test
        @DisplayName("存在しない Todo の場合は 404 を返すこと")
        void returnsNotFoundWith404ForNonExistentTodo() throws Exception {
            // Act
            ResponseEntity<String> response = restTemplate.exchange(
                    "/v1/todos/nonexistent",
                    HttpMethod.DELETE,
                    null,
                    String.class);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }

    /**
     * テスト用 Todo を作成して ID を返す
     */
    private String createTodo(String title, String description) {
        Todo todo = new Todo();
        todo.setTodoId(java.util.UUID.randomUUID().toString());
        todo.setTodoTitle(title);
        todo.setTodoDescription(description);
        todo.setFinished(false);
        todo.setCreatedAt(new java.util.Date());

        todoRepository.create(todo);
        return todo.getTodoId();
    }
}
