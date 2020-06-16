package com.example.infrastructure.mvc;

import lombok.Value;

@Value
public class FieldErrorResponse {
    String fieldName;
    String errorMessage;
}
