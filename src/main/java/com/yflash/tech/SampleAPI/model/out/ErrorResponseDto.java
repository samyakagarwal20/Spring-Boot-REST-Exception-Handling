package com.yflash.tech.SampleAPI.model.out;

import com.yflash.tech.SampleAPI.common.CommonConstants;
import com.yflash.tech.SampleAPI.exception.BadRequestException;
import com.yflash.tech.SampleAPI.exception.DetailsNotFoundException;
import com.yflash.tech.SampleAPI.exception.ProcessingException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ErrorResponseDto {

    protected static final String DATE_FORMAT = "yyyy.MM.dd.HH.mm.ss";
    private String error;
    private Integer code;
    private String errorCode;
    private String message;
    private List<String> details;
    private String timestamp;

   public ErrorResponseDto(String error, String message, List<String> errorMessages, Integer code, String timestamp, String errorCode) {
       this.error = error;
       this.code = code;
       this.errorCode = errorCode;
       this.message = message;
       this.details = errorMessages;
       this.timestamp = timestamp;
   }

   public static ErrorResponseDto badRequestException(BadRequestException exception) {
       return new ErrorResponseDto(CommonConstants.HTTP_STATUS_MESSAGE_BAD_REQUEST, exception.getMessage(), exception.getErrorMessages(), HttpStatus.BAD_REQUEST.value(), ErrorResponseDto.getTimestampNow(), exception.getErrorCode());
   }

    public static ErrorResponseDto detailsNotFoundException(DetailsNotFoundException exception) {
        return new ErrorResponseDto(CommonConstants.HTTP_STATUS_MESSAGE_DETAILS_NOT_FOUND, exception.getMessage(), exception.getErrorMessages(), HttpStatus.NOT_FOUND.value(), ErrorResponseDto.getTimestampNow(), exception.getErrorCode());
    }

    public static ErrorResponseDto processingException(ProcessingException exception) {
        return new ErrorResponseDto(CommonConstants.HTTP_STATUS_MESSAGE_INTERNAL_SERVER_ERROR, exception.getMessage(), exception.getErrorMessages(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorResponseDto.getTimestampNow(), exception.getErrorCode());
    }


   public static String getTimestampNow() {
       return new SimpleDateFormat(DATE_FORMAT).format(new Date());
   }

}
