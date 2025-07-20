package net.kem198.todos.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "net.kem198.todos" })
public class TodosCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosCoreApplication.class, args);
    }

}
