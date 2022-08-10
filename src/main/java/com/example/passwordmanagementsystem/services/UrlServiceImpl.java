package com.example.passwordmanagementsystem.services;

import org.springframework.beans.factory.annotation.Autowired;

public class UrlServiceImpl implements UrlService{
    @Autowired
    private UrlService urlService;
    @Autowired
    private UserService userService;
}
