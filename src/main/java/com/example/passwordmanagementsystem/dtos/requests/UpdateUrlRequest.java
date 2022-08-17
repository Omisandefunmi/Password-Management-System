package com.example.passwordmanagementsystem.dtos.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateUrlRequest {
    private String urlAddress;
    private String email;
    private String password;
    private String username;
}
