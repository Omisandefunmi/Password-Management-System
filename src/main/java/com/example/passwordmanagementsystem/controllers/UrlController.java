package com.example.passwordmanagementsystem.controllers;

import com.example.passwordmanagementsystem.dtos.requests.AddUrlRequest;
import com.example.passwordmanagementsystem.dtos.requests.UpdateUrlRequest;
import com.example.passwordmanagementsystem.dtos.responses.ApiResponse;
import com.example.passwordmanagementsystem.exceptions.PasswordManagementException;
import com.example.passwordmanagementsystem.services.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/url")
public class UrlController {
    @Autowired
    private UrlService urlService;

    @PostMapping("/addUrl")
    public ResponseEntity <?> addUrl(AddUrlRequest request){
        try{
            var serviceResponse = urlService.addUrl(request);
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(serviceResponse)
                    .message("Url added")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.ACCEPTED);
        }
        catch (PasswordManagementException ex){
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(ex.getMessage())
                    .message("Url adding fail")
                    .isSuccessful(false)
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/{urlAddress}")
    public ResponseEntity <?> findUrl(@PathVariable String urlAddress){
        try{
            return new ResponseEntity<>(urlService.findUrl(urlAddress), HttpStatus.FOUND);
        }
        catch(PasswordManagementException ex){
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(ex.getMessage())
                    .message("Url not found")
                    .isSuccessful(false)
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("updateUrl/{email}")
    public ResponseEntity <?> updateUrl(String email,  UpdateUrlRequest request){
        try{
            return new ResponseEntity<>(urlService.updateUrl(email, request), HttpStatus.OK);
        }
        catch (PasswordManagementException ex){
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(ex.getMessage())
                    .message("Url not updated")
                    .isSuccessful(false)
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("deleteUrl/{urlAddress}")
    public ResponseEntity <?> deleteUrl(String urlAddress){
        try{
            var serviceResponse = urlService.deleteUrl(urlAddress);
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(serviceResponse)
                    .message("Url deleted")
                    .isSuccessful(true)
                    .build();
            return new ResponseEntity<>(apiResponse, HttpStatus.OK);
        }
        catch(PasswordManagementException ex){
            ApiResponse apiResponse = ApiResponse.builder()
                    .data(ex.getMessage())
                    .message("Url not deleted")
                    .isSuccessful(false)
                    .build();

            return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        }
    }


}
