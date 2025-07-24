package net.kem198.todos.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// HACK: core 層にはエントリポイントは必要ないが、テスト実行時にエラーになるため残している
// できれば下記ドキュメントに倣ってクラスごと削除したい
// https://spring.pleiades.io/guides/gs/multi-module
@SpringBootApplication
public class TodosCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(TodosCoreApplication.class, args);
    }

}
