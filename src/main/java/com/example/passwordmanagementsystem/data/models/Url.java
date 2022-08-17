package com.example.passwordmanagementsystem.data.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("Urls")
@AllArgsConstructor
@Builder

public class Url {
    @Id
    private String id;
    private String urlAddress;
    private String urlEmail;
    private String username;
    private String urlPassword;

    public Url(String urlAddress, String urlPassword){
        this.urlAddress = urlAddress;
        this.urlPassword = urlPassword;
    }
}
