package net.kem198.todos.core.service.todo;

import java.util.Collection;

import net.kem198.todos.core.model.Todo;

public interface TodoService {
    Todo findOne(String todoId);

    Collection<Todo> findAll();

    Todo create(Todo todo);

    Todo finish(String todoId);

    void delete(String todoId);
}
