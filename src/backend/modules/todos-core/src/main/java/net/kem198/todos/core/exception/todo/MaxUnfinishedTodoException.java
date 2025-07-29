package net.kem198.todos.core.exception.todo;

import net.kem198.todos.core.exception.common.BusinessException;
import net.kem198.todos.core.exception.common.ErrorCode;

public class MaxUnfinishedTodoException extends BusinessException {
    public MaxUnfinishedTodoException(long maxCount) {
        super(ErrorCode.BUSINESS_ERROR,
                String.format("The count of un-finished Todo must not be over: %d", maxCount));
    }
}
