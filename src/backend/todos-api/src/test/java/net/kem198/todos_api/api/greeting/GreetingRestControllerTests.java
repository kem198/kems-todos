package net.kem198.todos_api.api.greeting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingRestControllerTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Nested
    class GetRequestTests {
        @Test
        @DisplayName("デフォルトの名前で挨拶を返す")
        void returnsGreetingWithDefaultName() {
            // Act
            ResponseEntity<String> response = restTemplate.getForEntity("/v1/greeting/hello",
                    String.class);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Hello, World!\n", response.getBody());
        }

        @Test
        @DisplayName("指定された名前で挨拶を返す")
        void returnsGreetingWithSpecifiedName() {
            // Act
            ResponseEntity<String> response = restTemplate.getForEntity("/v1/greeting/hello?name=KeM198",
                    String.class);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals("Hello, KeM198!\n" + //
                    "", response.getBody());
        }
    }
}
