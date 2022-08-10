package com.example.passwordmanagementsystem.services;

import com.example.passwordmanagementsystem.data.repositories.UserRepository;
import com.example.passwordmanagementsystem.dtos.requests.RegisterUserRequest;
import com.example.passwordmanagementsystem.dtos.responses.RegisterUserResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

//    @AfterEach
//    void tearDown() {
//    }

    @Test
    void testThatUserServiceCanRegisterUser(){
        RegisterUserRequest registerUserRequest = new RegisterUserRequest();
        registerUserRequest.setEmail("let@mail.com");
        registerUserRequest.setPassword("REDSEA");
        registerUserRequest.setPhoneNumber("0907865320");
        assertEquals(0, userService.count());

        RegisterUserResponse response = userService.registerUser(registerUserRequest);
        assertEquals("Success", response.getMessage());
        assertEquals(1, userRepository.count());
    }
}