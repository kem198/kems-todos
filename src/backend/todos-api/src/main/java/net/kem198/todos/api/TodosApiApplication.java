package net.kem198.todos.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "net.kem198.todos" })
public class TodosApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosApiApplication.class, args);
    }

}
