package com.fullstack.commonservice.model;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.util.Map;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorValidation {
    private String errorCode;
    private Map<String, String> errorMessages;
    private HttpStatus httpStatus;
}
