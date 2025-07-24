package net.kem198.todos.infrastructure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// HACK: infrastructure 層にはエントリポイントは必要ないが、テスト実行のため残している
// できれば消したい
@SpringBootApplication(scanBasePackages = "net.kem198.todos")
public class TodosInfrastructureApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosInfrastructureApplication.class, args);
    }

}
