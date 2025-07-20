package net.kem198.todos.api.fizzbuzz;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FizzBuzzResourceMapper {

    FizzBuzzResource toResource(String result);

    // TODO: Change to FizzBuzz Model
    Object toDomain(FizzBuzzResource fizzBuzzResource);
}
