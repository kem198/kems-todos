package net.kem198.todos.infrastructure.mapper;

import org.mapstruct.Mapper;

import net.kem198.todos.core.model.Todo;
import net.kem198.todos.infrastructure.entity.Todos;

@Mapper(componentModel = "spring")
public interface TodosMapper {

    Todo toDomain(Todos todoResource);

    Todos toEntity(Todo todo);
}
