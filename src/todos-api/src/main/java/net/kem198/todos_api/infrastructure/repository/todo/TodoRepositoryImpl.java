package net.kem198.todos_api.infrastructure.repository.todo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.kem198.todos_api.domain.model.Todo;
import net.kem198.todos_api.domain.repository.todo.TodoRepository;

@Repository
public class TodoRepositoryImpl implements TodoRepository {
    private final JdbcTemplate jdbcTemplate;

    public TodoRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Todo> TODO_ROW_MAPPER = new RowMapper<Todo>() {
        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();
            todo.setTodoId(rs.getString("todo_id"));
            todo.setTodoTitle(rs.getString("todo_title"));
            todo.setTodoDescription(rs.getString("todo_description"));
            todo.setFinished(rs.getBoolean("finished"));
            todo.setCreatedAt(rs.getTimestamp("created_at"));
            return todo;
        }
    };

    @Override
    public Todo findById(String todoId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT todo_id, todo_title, todo_description, finished, created_at FROM todos WHERE todo_id = ?",
                    TODO_ROW_MAPPER, todoId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Collection<Todo> findAll() {
        return jdbcTemplate.query(
                "SELECT todo_id, todo_title, todo_description, finished, created_at FROM todos ORDER BY created_at",
                TODO_ROW_MAPPER);
    }

    @Override
    public void create(Todo todo) {
        jdbcTemplate.update(
                "INSERT INTO todos (todo_id, todo_title, todo_description, finished, created_at) VALUES (?, ?, ?, ?, ?)",
                todo.getTodoId(), todo.getTodoTitle(), todo.getTodoDescription(), todo.isFinished(),
                todo.getCreatedAt());
    }

    @Override
    public boolean update(Todo todo) {
        int updated = jdbcTemplate.update(
                "UPDATE todos SET todo_title = ?, todo_description = ?, finished = ? WHERE todo_id = ?",
                todo.getTodoTitle(), todo.getTodoDescription(), todo.isFinished(), todo.getTodoId());
        return updated > 0;
    }

    @Override
    public void delete(Todo todo) {
        jdbcTemplate.update("DELETE FROM todos WHERE todo_id = ?", todo.getTodoId());
    }

    @Override
    public long countByFinished(boolean finished) {
        Long count = jdbcTemplate.queryForObject("SELECT count(*) FROM todos WHERE finished = ?", Long.class, finished);
        return count != null ? count : 0;
    }
}
