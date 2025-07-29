package net.kem198.todos.core.exception.todo;

import net.kem198.todos.core.exception.common.BusinessException;
import net.kem198.todos.core.exception.common.ErrorCode;

public class AlreadyFinishedTodoException extends BusinessException {
    public AlreadyFinishedTodoException() {
        super(ErrorCode.BUSINESS_ERROR, "The Todo is already finished.");
    }
}
