package com.fullstack.commonservice.advice;

import com.fullstack.commonservice.model.ErrorMessage;
import com.fullstack.commonservice.model.ErrorValidation;
import jakarta.validation.ConstraintViolation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorValidation> handleValidationExceptions(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        var result = ex.getBindingResult().getAllErrors().stream().map(item -> item.unwrap(ConstraintViolation.class)).toList();
        result.forEach(error -> errors.put(error.getPropertyPath().toString(), error.getMessageTemplate()));
        ErrorValidation errorValidation = ErrorValidation.builder()
                .errorCode("1111")
                .errorMessages(errors)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .build();
        return new ResponseEntity<>(errorValidation,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleException(Exception ex) {
        ErrorMessage errorMessage = new ErrorMessage("9999", ex.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}