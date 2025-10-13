package net.kem198.todos.api.common.config;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class WebMvcConfigTests {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Nested
    @DisplayName("CORS 設定のテスト")
    class AddCorsMappingsTests {

        @Test
        @DisplayName("許可されたオリジンからのリクエストに対して CORS ヘッダが設定されること")
        void shouldSetCorsHeadersForAllowedOrigin() throws Exception {
            // Arrange
            String allowedOrigin = "http://localhost:3000";

            // Act
            ResultActions result = mockMvc.perform(get("/v1/greeting/hello")
                    .header("Origin", allowedOrigin));

            // Assert
            result.andExpect(status().isOk())
                    .andExpect(header().string("Access-Control-Allow-Origin", allowedOrigin))
                    .andExpect(header().string("Access-Control-Allow-Credentials", "true"));
        }

        @Test
        @DisplayName("プリフライトリクエストに対して適切な CORS ヘッダが設定されること")
        void shouldHandlePreflightRequestWithCorsHeaders() throws Exception {
            // Arrange
            String allowedOrigin = "http://localhost:3000";
            String requestMethod = "GET";
            String requestHeaders = "Content-Type";

            // Act
            ResultActions result = mockMvc.perform(options("/v1/greeting/hello")
                    .header("Origin", allowedOrigin)
                    .header("Access-Control-Request-Method", requestMethod)
                    .header("Access-Control-Request-Headers", requestHeaders));

            // Assert
            result.andExpect(status().isOk())
                    .andExpect(header().string("Access-Control-Allow-Origin", allowedOrigin))
                    .andExpect(header().string("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS"))
                    .andExpect(header().string("Access-Control-Allow-Headers", requestHeaders))
                    .andExpect(header().string("Access-Control-Allow-Credentials", "true"));
        }

    }
}
