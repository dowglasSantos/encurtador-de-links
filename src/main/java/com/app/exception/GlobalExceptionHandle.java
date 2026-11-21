package com.app.exception;

import com.app.dto.ResponseExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(LinkAlreadyRegisteredException.class)
    private ResponseEntity<ResponseExceptionHandler> linkAlreadyRegistered(LinkAlreadyRegisteredException e) {
        ResponseExceptionHandler responseExceptionHandler = new ResponseExceptionHandler(
                HttpStatus.CONFLICT.value(),
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(responseExceptionHandler);
    }

    @ExceptionHandler(InvalidLinkException.class)
    private ResponseEntity<ResponseExceptionHandler> invalidLink(InvalidLinkException e) {
        ResponseExceptionHandler responseExceptionHandler = new ResponseExceptionHandler(
                HttpStatus.UNPROCESSABLE_CONTENT.value(),
                e.getMessage()
        );

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(responseExceptionHandler);
    }
}
