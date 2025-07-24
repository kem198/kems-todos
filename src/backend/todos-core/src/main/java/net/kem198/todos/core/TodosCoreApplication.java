package net.kem198.todos.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// HACK: infrastructure 層にはエントリポイントは必要ないが、テスト実行のため残している
// できれば下記ドキュメントに倣って消したい
// https://spring.pleiades.io/guides/gs/multi-module
@SpringBootApplication(scanBasePackages = "net.kem198.todos")
public class TodosCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosCoreApplication.class, args);
    }

}
