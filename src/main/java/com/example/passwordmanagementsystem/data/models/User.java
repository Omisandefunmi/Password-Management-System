package com.example.passwordmanagementsystem.data.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;
@Setter
@Getter
@NoArgsConstructor
@Document("Users")
public class User {
    @Id
    private String id;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isLoggedIn;
    @DBRef
    private Set<Url> urls = new HashSet<>();

}
