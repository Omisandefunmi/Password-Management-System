package com.example.passwordmanagementsystem.dtos.responses;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class RegisterUserResponse {
    private String message;
    private String email;
    private String phoneNumber;
    private String username;
}
