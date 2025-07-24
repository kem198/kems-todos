package net.kem198.todos.core.exception.common;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String resourceName, Object resourceId) {
        super(HttpStatus.NOT_FOUND, "[E002] [" + resourceName + "] Resource is not found: " + resourceId);
    }
}
