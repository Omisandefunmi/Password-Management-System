package com.example.passwordmanagementsystem.dtos.responses;

import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class UrlDto {
    private String urlAddress;
    private String email;
    private String message;
}
