package com.example.passwordmanagementsystem.data.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("Urls")
public class Url {
    @Id
    private String id;
    private String urlAddress;
    private String urlEmail;
    private String urlPassword;
}
