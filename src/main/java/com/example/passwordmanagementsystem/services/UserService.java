package com.example.passwordmanagementsystem.services;

import com.example.passwordmanagementsystem.dtos.requests.RegisterUserRequest;
import com.example.passwordmanagementsystem.dtos.responses.RegisterUserResponse;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);
    int count();
}
