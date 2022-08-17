package com.example.passwordmanagementsystem.dtos.responses;

import com.example.passwordmanagementsystem.data.models.Url;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class DeleteUrlResponse {
    private Url url;
    private String message;
}
