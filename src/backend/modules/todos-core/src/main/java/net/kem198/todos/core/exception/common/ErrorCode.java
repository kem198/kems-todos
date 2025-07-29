package net.kem198.todos.core.exception.common;

public enum ErrorCode {
    RESOURCE_NOT_FOUND("E404"),
    BUSINESS_ERROR("E400");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
