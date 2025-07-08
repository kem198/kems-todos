package net.kem198.todos_api.api.todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
        @DisplayName("Todo 一覧を取得できる")
        void returnsTodoList() throws Exception {
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
        @DisplayName("指定された ID の Todo を取得できる")
        void returnsTodoById() throws Exception {
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
        @DisplayName("存在しない ID の場合は 404 を返す")
        void returnsNotFoundForNonExistentId() throws Exception {
            // Act
            ResponseEntity<String> response = restTemplate.getForEntity("/v1/todos/nonexistent", String.class);

            // Assert
            assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        }
    }

    @Nested
    class PostTodoTests {
        @Test
        @DisplayName("正常なボディの場合 201 と所定の正常レスポンスを返す")
        void returnsCreatedWithResponsesForValidBody() throws Exception {
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
        @DisplayName("未完了 Todo が上限に達している場合は 400 を返す")
        void returnsBadRequestWhenUnfinishedTodoLimitReached() throws Exception {
            // Arrange - 未完了Todoを上限まで作成（5個）
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
        @DisplayName("バリデーションエラーの場合は 400 を返す")
        void returnsBadRequestForValidationError() throws Exception {
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
    }

    @Nested
    class PutTodoFinishTests {
        @Test
        @DisplayName("Todo を完了状態にできる")
        void finishesTodo() throws Exception {
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
        @DisplayName("既に完了済みの Todo を完了しようとすると 400 を返す")
        void returnsBadRequestForAlreadyFinishedTodo() throws Exception {
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
        @DisplayName("存在しない Todo を完了しようとすると 404 を返す")
        void returnsNotFoundForNonExistentTodo() throws Exception {
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
        @DisplayName("Todo を削除できる")
        void deletesTodo() throws Exception {
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

            // 削除後、取得しようとすると404になることを確認
            ResponseEntity<String> getResponse = restTemplate.getForEntity("/v1/todos/" + todoId, String.class);
            assertEquals(HttpStatus.NOT_FOUND, getResponse.getStatusCode());
        }

        @Test
        @DisplayName("存在しない Todo を削除しようとすると 404 を返す")
        void returnsNotFoundForNonExistentTodo() throws Exception {
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

    // ヘルパーメソッド：テスト用 Todo を作成し、ID を返す
    private String createTodo(String title, String description) throws Exception {
        String requestBody = String.format("""
                    {
                        "todoTitle": "%s",
                        "todoDescription": "%s"
                    }
                """, title, description);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response = restTemplate.postForEntity("/v1/todos", requestEntity, String.class);
        JsonNode responseBody = objectMapper.readTree(response.getBody());
        return responseBody.get("todoId").asText();
    }
}
