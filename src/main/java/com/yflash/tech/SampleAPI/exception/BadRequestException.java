package com.yflash.tech.SampleAPI.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BadRequestException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private final String message;
    private final String errorCode;
    private final List<String> errorMessages;

    public BadRequestException(String message, String errorCode, List<String> errorMessages) {
        this.message = message;
        this.errorCode = errorCode;
        this.errorMessages = errorMessages;
    }

}
