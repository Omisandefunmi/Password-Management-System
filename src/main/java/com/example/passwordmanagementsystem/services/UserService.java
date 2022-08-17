package com.example.passwordmanagementsystem.services;

import com.example.passwordmanagementsystem.data.models.User;
import com.example.passwordmanagementsystem.dtos.requests.RegisterUserRequest;
import com.example.passwordmanagementsystem.dtos.requests.UpdateUserRequest;
import com.example.passwordmanagementsystem.dtos.responses.RegisterUserResponse;
import com.example.passwordmanagementsystem.dtos.responses.UpdateUserResponse;
import com.example.passwordmanagementsystem.dtos.responses.UserDto;

import java.util.Optional;

public interface UserService {
    RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest);
    int count();

    UserDto findUser(String id);

    UpdateUserResponse updateUser(String id, UpdateUserRequest updateUserRequest);

    Optional<User> findUserBy(String email);
}
