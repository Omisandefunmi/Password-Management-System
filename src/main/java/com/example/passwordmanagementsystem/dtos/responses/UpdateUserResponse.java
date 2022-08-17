package com.example.passwordmanagementsystem.dtos.responses;

import com.example.passwordmanagementsystem.data.models.Url;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@AllArgsConstructor
@Builder
public class UpdateUserResponse {
    private String username;
    private String emailAddress;
    private String phoneNumber;
    private Set<Url> urlSet = new HashSet<>();
}
