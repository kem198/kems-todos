DROP TABLE IF EXISTS examples;

CREATE TABLE
    IF NOT EXISTS examples (
        id SERIAL PRIMARY KEY,
        name VARCHAR(100) NOT NULL,
        created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

INSERT INTO
    examples (name)
VALUES
    ('Example 1'),
    ('Example 2'),
    ('Example 3');
