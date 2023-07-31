package com.yflash.tech.SampleAPI.exception;

import com.google.gson.Gson;
import com.yflash.tech.SampleAPI.controller.UserController;
import com.yflash.tech.SampleAPI.model.out.ErrorResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(assignableTypes = {UserController.class})
public class ControllerExceptionHandler {

    private Gson gson = new Gson();
    private static final Logger LOGGER = LogManager.getLogger(ControllerExceptionHandler.class);

    @PostMapping(produces = {"application/problem+json"})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleException(BadRequestException exception) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.badRequestException(exception);
        String errorResponseJson = gson.toJson(errorResponseDto);
        LOGGER.error(errorResponseJson);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseDto);
    }

    @PostMapping(produces = {"application/problem+json"})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(DetailsNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleException(DetailsNotFoundException exception) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.detailsNotFoundException(exception);
        String errorResponseJson = gson.toJson(errorResponseDto);
        LOGGER.error(errorResponseJson);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseDto);
    }

    @PostMapping(produces = {"application/problem+json"})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ProcessingException.class)
    public ResponseEntity<ErrorResponseDto> handleException(ProcessingException exception) {
        ErrorResponseDto errorResponseDto = ErrorResponseDto.processingException(exception);
        String errorResponseJson = gson.toJson(errorResponseDto);
        LOGGER.error(errorResponseJson);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseDto);
    }

}
