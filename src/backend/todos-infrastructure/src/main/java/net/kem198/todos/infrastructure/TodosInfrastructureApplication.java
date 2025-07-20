package net.kem198.todos.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "net.kem198.todos" })
public class TodosInfrastructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosInfrastructureApplication.class, args);
    }

}
