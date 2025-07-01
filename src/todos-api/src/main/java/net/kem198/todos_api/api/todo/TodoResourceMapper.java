package net.kem198.todos_api.api.todo;

import org.mapstruct.Mapper;

import net.kem198.todos_api.domain.model.Todo;

@Mapper(componentModel = "spring")
public interface TodoResourceMapper {

    TodoResource map(Todo todo);

    Todo map(TodoResource todoResource);
}
