package net.kem198.todos.core.exception.common;

public class ResourceNotFoundException extends BusinessException {
    public ResourceNotFoundException(String resourceName) {
        super(ErrorCode.RESOURCE_NOT_FOUND, String.format("Resource is not found: %s", resourceName));
    }

    public ResourceNotFoundException(String resourceKey, Object resourceValue) {
        super(ErrorCode.RESOURCE_NOT_FOUND, String.format("Resource is not found: %s: %s", resourceKey, resourceValue));
    }
}
