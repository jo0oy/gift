package com.jo0oy.gift.common.exception;

import com.jo0oy.gift.common.response.ErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private ErrorCode errorCode;

    public BaseException() {

    }

    public BaseException(ErrorCode errorCode) {
        super(errorCode.getErrorMsg());
        this.errorCode = errorCode;
    }

    public BaseException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BaseException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
}
