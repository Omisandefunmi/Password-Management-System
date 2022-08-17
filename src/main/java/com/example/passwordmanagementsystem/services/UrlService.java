package com.example.passwordmanagementsystem.services;

import com.example.passwordmanagementsystem.dtos.requests.AddUrlRequest;
import com.example.passwordmanagementsystem.dtos.requests.UpdateUrlRequest;
import com.example.passwordmanagementsystem.dtos.responses.DeleteUrlResponse;
import com.example.passwordmanagementsystem.dtos.responses.UpdateUrlResponse;
import com.example.passwordmanagementsystem.dtos.responses.UrlDto;

public interface UrlService {
    UrlDto addUrl(AddUrlRequest request);

    UrlDto findUrl(String urlAddress);

    UpdateUrlResponse updateUrl(String email, UpdateUrlRequest request);

    DeleteUrlResponse deleteUrl(String urlAddress);
}
