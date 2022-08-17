package com.example.passwordmanagementsystem.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ApiResponse {
    private boolean isSuccessful;
    private String message;
    private Object data;
}
