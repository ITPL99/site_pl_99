package com.example.site_pl_99.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Универсальный wrapper для всех API ответов.
 * Стандартизирует формат ответа независимо от типа операции.
 *
 * @param <T> тип данных в поле data
 */
@Schema(description = "Универсальный формат ответа API")
public class ApiResponseWrapper<T> {

    @Schema(description = "Статус операции", example = "SUCCESS", allowableValues = {"SUCCESS", "ERROR"})
    private String status;

    @Schema(description = "Человекочитаемое сообщение", example = "Operation completed successfully")
    private String message;

    @Schema(description = "Данные ответа (может быть null)")
    private T data;

    @Schema(description = "Список ошибок валидации (только при status=ERROR)")
    private List<FieldError> errors;

    @Schema(description = "Временная метка ответа", example = "2026-04-23T10:30:00")
    private LocalDateTime timestamp;

    /**
     * Внутренний класс для ошибок валидации отдельных полей
     */
    @Schema(description = "Ошибка валидации конкретного поля")
    public static class FieldError {
        @Schema(description = "Имя поля", example = "username")
        private String field;

        @Schema(description = "Сообщение об ошибке", example = "Username is required")
        private String message;

        public FieldError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    private ApiResponseWrapper(Builder<T> builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.data = builder.data;
        this.errors = builder.errors;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> Builder<T> builder() {
        return new Builder<>();
    }

    public static <T> ApiResponseWrapper<T> success(T data, String message) {
        return ApiResponseWrapper.<T>builder()
                .status("SUCCESS")
                .message(message)
                .data(data)
                .build();
    }

    public static <T> ApiResponseWrapper<T> success(String message) {
        return ApiResponseWrapper.<T>builder()
                .status("SUCCESS")
                .message(message)
                .build();
    }

    public static <T> ApiResponseWrapper<T> error(String message) {
        return ApiResponseWrapper.<T>builder()
                .status("ERROR")
                .message(message)
                .build();
    }

    public static <T> ApiResponseWrapper<T> error(String message, List<FieldError> errors) {
        return ApiResponseWrapper.<T>builder()
                .status("ERROR")
                .message(message)
                .errors(errors)
                .build();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static class Builder<T> {
        private String status;
        private String message;
        private T data;
        private List<FieldError> errors;

        public Builder<T> status(String status) {
            this.status = status;
            return this;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public Builder<T> data(T data) {
            this.data = data;
            return this;
        }

        public Builder<T> errors(List<FieldError> errors) {
            this.errors = errors;
            return this;
        }

        public ApiResponseWrapper<T> build() {
            return new ApiResponseWrapper<>(this);
        }
    }
}
