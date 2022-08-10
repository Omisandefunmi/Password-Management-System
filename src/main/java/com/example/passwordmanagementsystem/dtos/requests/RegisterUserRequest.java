package com.example.passwordmanagementsystem.dtos.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RegisterUserRequest {
    private String email;
    private String password;
    private String phoneNumber;
}
