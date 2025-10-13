package net.kem198.todos.api.common.config;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebMvcConfigTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Nested
    @DisplayName("CORS 設定のテスト")
    class AddCorsMappingsTests {

        @Test
        @DisplayName("許可されたオリジンからのリクエストに対して CORS ヘッダが設定されること")
        void shouldSetCorsHeadersForAllowedOrigin() throws Exception {
            // Arrange
            String allowedOrigin = "http://localhost:3000";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Origin", allowedOrigin);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Act
            ResponseEntity<String> response = restTemplate.exchange(
                    "/v1/greeting/hello",
                    HttpMethod.GET,
                    requestEntity,
                    String.class);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(allowedOrigin, response.getHeaders().getFirst("Access-Control-Allow-Origin"));
            assertEquals("true", response.getHeaders().getFirst("Access-Control-Allow-Credentials"));
        }

        @Test
        @DisplayName("プリフライトリクエストに対して適切な CORS ヘッダが設定されること")
        void shouldHandlePreflightRequestWithCorsHeaders() throws Exception {
            // Arrange
            String allowedOrigin = "http://localhost:3000";
            String requestMethod = "GET";
            String requestHeaders = "Content-Type";

            HttpHeaders headers = new HttpHeaders();
            headers.set("Origin", allowedOrigin);
            headers.set("Access-Control-Request-Method", requestMethod);
            headers.set("Access-Control-Request-Headers", requestHeaders);
            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            // Act
            ResponseEntity<String> response = restTemplate.exchange(
                    "/v1/greeting/hello",
                    HttpMethod.OPTIONS,
                    requestEntity,
                    String.class);

            // Assert
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(allowedOrigin, response.getHeaders().getFirst("Access-Control-Allow-Origin"));
            assertTrue(response.getHeaders().getFirst("Access-Control-Allow-Methods")
                    .contains("GET,POST,PUT,DELETE,OPTIONS"));
            assertEquals(requestHeaders, response.getHeaders().getFirst("Access-Control-Allow-Headers"));
            assertEquals("true", response.getHeaders().getFirst("Access-Control-Allow-Credentials"));
        }

    }
}
