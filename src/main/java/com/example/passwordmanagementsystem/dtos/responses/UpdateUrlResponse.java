package com.example.passwordmanagementsystem.dtos.responses;

import com.example.passwordmanagementsystem.data.models.Url;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
public class UpdateUrlResponse {
    private boolean isUpdated;
    private Url url;
}
