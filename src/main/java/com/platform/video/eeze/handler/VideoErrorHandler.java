package com.platform.video.eeze.handler;

import com.platform.video.eeze.dto.ErrorDTO;
import com.platform.video.eeze.exceptions.VideoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class VideoErrorHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (existing, replacement) -> existing));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ErrorDTO
                .builder()
                .generalMessage("Some data is invalid")
                .errorDescription(errors)
                .build());
    }

    @ExceptionHandler(VideoNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleVideoNotFoundException(VideoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ErrorDTO
                .builder()
                .generalMessage(ex.getMessage())
                .build());
    }
}
