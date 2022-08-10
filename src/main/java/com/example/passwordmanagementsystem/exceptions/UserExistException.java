package com.example.passwordmanagementsystem.exceptions;

public class UserExistException extends PasswordManagementException {
    public UserExistException(String message) {
        super(message);
    }
}
