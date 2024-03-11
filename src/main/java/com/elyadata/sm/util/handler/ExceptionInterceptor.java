package com.elyadata.sm.util.handler;


import com.elyadata.sm.util.exception.AbstractAppException;
import com.elyadata.sm.util.exception.BadRequestException;
import com.elyadata.sm.util.exception.model.ErrorResponse;
import com.elyadata.sm.util.tools.Constants;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ExceptionInterceptor {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> entityNotFoundException(EntityNotFoundException exception) {
        log.error(exception.getMessage());
        return new ResponseEntity<>(createCommonErrors(HttpStatus.NOT_FOUND.value(), exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AbstractAppException.class)
    public ResponseEntity<ErrorResponse> appExceptionHandler(AbstractAppException exception) {
        return null;
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> backendExceptionHandler(BadRequestException badRequestException) {
        return new ResponseEntity<>(createCommonErrors(HttpStatus.BAD_REQUEST.value(), badRequestException.getMainResource()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception exception) {
        log.error("Generic exception handler", exception.getCause());
        exception.printStackTrace();
        return new ResponseEntity<>(createCommonErrors(HttpStatus.INTERNAL_SERVER_ERROR.value(), Constants.UNKNOWN), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodInvalidExceptionHandler(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(createCommonErrors(HttpStatus.BAD_REQUEST.value(), exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse createCommonErrors(Integer httpStatus, String message) {
        return ErrorResponse.builder().httpStatus(httpStatus).message(message).timestamp(LocalDateTime.now()).build();
    }


}
