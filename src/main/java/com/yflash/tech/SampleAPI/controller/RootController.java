package com.yflash.tech.SampleAPI.controller;

import com.yflash.tech.SampleAPI.exception.BadRequestException;
import com.yflash.tech.SampleAPI.exception.DetailsNotFoundException;
import com.yflash.tech.SampleAPI.exception.ProcessingException;
import com.yflash.tech.SampleAPI.model.out.ErrorResponseDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    private static final Logger LOGGER = LogManager.getLogger(RootController.class);

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleException(BadRequestException exception) {
        return new ResponseEntity<>(new ErrorResponseDto(exception.getMessage(), exception.getErrorCode(), exception.getErrorMessages()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DetailsNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleException(DetailsNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponseDto(exception.getMessage(), exception.getErrorCode(), exception.getErrorMessages()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProcessingException.class)
    public ResponseEntity<ErrorResponseDto> handleException(ProcessingException exception) {
        return new ResponseEntity<>(new ErrorResponseDto(exception.getMessage(), exception.getErrorCode(), exception.getErrorMessages()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
