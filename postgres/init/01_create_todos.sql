DROP TABLE IF EXISTS todos;

CREATE TABLE
    todos (
        todo_id VARCHAR(255) PRIMARY KEY,
        todo_title VARCHAR(30) NOT NULL,
        todo_description VARCHAR(50),
        finished BOOLEAN NOT NULL DEFAULT FALSE,
        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
    );
