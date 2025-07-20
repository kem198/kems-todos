package net.kem198.todos.core.domain.exception.todo;

import org.springframework.http.HttpStatus;

import net.kem198.todos.core.domain.exception.common.BusinessException;

public class AlreadyFinishedTodoException extends BusinessException {
    public AlreadyFinishedTodoException(String resourceName) {
        super(HttpStatus.BAD_REQUEST,
                "[E003] [" + resourceName + "] The Todo is already finished.");
    }
}
