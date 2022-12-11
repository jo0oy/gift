package com.jo0oy.gift.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.FieldError;

@Getter
@NoArgsConstructor
public class Errors<T> {
    private ErrorCode errorCode;
    private String errorMessage;
    private T errors;

    @Builder
    public Errors(final ErrorCode errorCode,
                  final String errorMessage,
                  final T errors) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errors = errors;
    }

    public static <T> Errors<T> of(final ErrorCode errorCode) {
        return of(errorCode, null);
    }

    public static <T> Errors<T> of(final ErrorCode errorCode, final T errors) {
        return Errors.<T>builder()
                .errorCode(errorCode)
                .errorMessage(errorCode.getErrorMsg())
                .errors(errors)
                .build();
    }

    @Getter
    @NoArgsConstructor
    public static class ValidationError {
        private String field;
        private String message;

        @Builder
        public ValidationError(final String field, final String message) {
            this.field = field;
            this.message = message;
        }

        public static ValidationError of(final FieldError fieldError) {
            return ValidationError.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
        }
    }
}