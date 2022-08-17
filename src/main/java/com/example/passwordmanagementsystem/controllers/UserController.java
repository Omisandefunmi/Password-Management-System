package com.example.passwordmanagementsystem.controllers;

import com.example.passwordmanagementsystem.dtos.requests.RegisterUserRequest;
import com.example.passwordmanagementsystem.dtos.requests.UpdateUserRequest;
import com.example.passwordmanagementsystem.dtos.responses.ApiResponse;
import com.example.passwordmanagementsystem.exceptions.PasswordManagementException;
import com.example.passwordmanagementsystem.exceptions.UserExistException;
import com.example.passwordmanagementsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(RegisterUserRequest request){
        try{
            var serviceResponse = userService.registerUser(request);
            ApiResponse apiResponse = ApiResponse.builder()
                    .isSuccessful(true)
                    .data(serviceResponse)
                    .message("User registered")
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        }
        catch(UserExistException ex){
            ApiResponse apiResponse = ApiResponse.builder()
                    .isSuccessful(false)
                    .data(ex.getMessage())
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> findUserById(@PathVariable String id){
        try{
            var serviceResponse = userService.findUser(id);
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(serviceResponse)
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        catch(PasswordManagementException ex){
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(ex.getMessage())
                    .message("User not found")
                    .isSuccessful(false)
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity <?> updateUser(@PathVariable String id, UpdateUserRequest updateUserRequest){
        try{
            var serviceResponse = userService.updateUser(id, updateUserRequest);
            ApiResponse apiResponse = ApiResponse.builder()
                    .isSuccessful(true)
                    .data(serviceResponse)
                    .message("Update Successful")
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        catch (PasswordManagementException ex){
            ApiResponse apiResponse = ApiResponse.builder()
                    .isSuccessful(false)
                    .message("Unsuccessful Operation")
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }


}
