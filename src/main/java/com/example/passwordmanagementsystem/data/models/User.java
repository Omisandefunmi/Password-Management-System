package com.example.passwordmanagementsystem.data.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("Users")
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String phoneNumber;
    private String username;
    private boolean isLoggedIn;
    @DBRef
    private Set<Url> urls = new HashSet<>();

}
