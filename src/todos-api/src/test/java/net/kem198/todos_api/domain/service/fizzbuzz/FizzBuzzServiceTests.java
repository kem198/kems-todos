package net.kem198.todos_api.domain.service.fizzbuzz;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FizzBuzzServiceTests {
    @Autowired
    private FizzBuzzService fizzBuzzService;

    @Nested
    class ProcessFizzBuzzMethodTests {
        @Test
        @DisplayName("数値を与えると FizzBuzz の結果を返す")
        void returnsResultOfFizzBuzzForNumber() {
            // Act
            String actual = fizzBuzzService.processFizzBuzz(3);

            // Assert
            assertEquals("Fizz", actual);
        }
    }
}
