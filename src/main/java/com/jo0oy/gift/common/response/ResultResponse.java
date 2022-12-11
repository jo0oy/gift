package com.jo0oy.gift.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
public class ResultResponse<T> {
    private boolean success;
    private ZonedDateTime responseTime;
    private String message;
    private T data;
    private Errors<?> error;

    @Builder
    public ResultResponse(final boolean success,
                          final ZonedDateTime responseTime,
                          final String message,
                          final T data,
                          final Errors<?> error) {
        this.success = success;
        this.responseTime = responseTime;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public static <T> ResultResponse<T> success(final String message) {
        return success(message, null);
    }

    public static <T> ResultResponse<T> success(final T data) {
        return success(null, data);
    }

    public static <T> ResultResponse<T> success(final String message, final T data) {
        return ResultResponse.<T>builder()
                .success(true)
                .responseTime(ZonedDateTime.now())
                .message(message)
                .data(data)
                .error(null)
                .build();
    }

    public static <T> ResultResponse<T> fail(final String message, final Errors<T> error) {
        return ResultResponse.<T>builder()
                .success(false)
                .responseTime(ZonedDateTime.now())
                .message(message)
                .data(null)
                .error(error)
                .build();
    }
}

