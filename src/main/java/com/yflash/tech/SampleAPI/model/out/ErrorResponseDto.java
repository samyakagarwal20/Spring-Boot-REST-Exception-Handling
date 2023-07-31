package com.yflash.tech.SampleAPI.model.out;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ErrorResponseDto {
    private final String message;
    private final String errorCode;
    private final List<String> errorMessages;

    public ErrorResponseDto(String message, String errorCode, List<String> errorMessages) {
        this.message = message;
        this.errorCode = errorCode;
        this.errorMessages = errorMessages;
    }
}
