package net.kem198.todos_api.api.todo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.kem198.todos_api.domain.model.Todo;
import net.kem198.todos_api.domain.service.todo.TodoService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/v1/todos")
public class TodoRestController {

    private final TodoService todoService;
    private final TodoResourceMapper beanMapper;

    public TodoRestController(TodoService todoService, TodoResourceMapper beanMapper) {
        this.todoService = todoService;
        this.beanMapper = beanMapper;
    }

    @GetMapping
    public ResponseEntity<List<TodoResource>> getTodos() {
        Collection<Todo> todos = todoService.findAll();
        List<TodoResource> todoResources = new ArrayList<>();
        for (Todo todo : todos) {
            todoResources.add(beanMapper.map(todo));
        }
        return ResponseEntity.ok(todoResources);
    }

    @PostMapping
    public ResponseEntity<TodoResource> postTodos(@RequestBody @Validated TodoResource todoResource) {
        Todo createdTodo = todoService.create(beanMapper.map(todoResource));
        TodoResource createdTodoResponse = beanMapper.map(createdTodo);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTodoResponse);
    }

    @GetMapping("{todoId}")
    public ResponseEntity<TodoResource> getTodo(@PathVariable("todoId") String todoId) {
        Todo todo = todoService.findOne(todoId);
        TodoResource todoResource = beanMapper.map(todo);
        return ResponseEntity.ok(todoResource);
    }

    @PutMapping("{todoId}")
    public ResponseEntity<TodoResource> putTodo(@PathVariable("todoId") String todoId) {
        Todo finishedTodo = todoService.finish(todoId);
        TodoResource finishedTodoResource = beanMapper.map(finishedTodo);
        return ResponseEntity.ok(finishedTodoResource);
    }

    @DeleteMapping("{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("todoId") String todoId) {
        todoService.delete(todoId);
        return ResponseEntity.noContent().build();
    }
}
