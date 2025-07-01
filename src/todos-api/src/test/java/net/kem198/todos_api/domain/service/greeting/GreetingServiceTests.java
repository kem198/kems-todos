package net.kem198.todos_api.domain.service.greeting;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import net.kem198.todos_api.api.greeting.GreetingResource;

@SpringBootTest
public class GreetingServiceTests {
    @Autowired
    private GreetingService GreetingService;

    @Nested
    class ProcessGreetingMethodTests {
        @Test
        @DisplayName("文字列を与えると GreetingDto 型の結果を返す")
        void returnsGreetingDtoForString() {
            // Act
            GreetingResource greetingDto = GreetingService.processGreeting("World");

            // Assert
            assertEquals(new GreetingResource(1, "Hello, World!"), greetingDto);
        }
    }
}
