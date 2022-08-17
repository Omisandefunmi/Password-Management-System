package com.example.passwordmanagementsystem.data.repositories;

import com.example.passwordmanagementsystem.data.models.Url;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends MongoRepository <Url, String> {

    Url findByUrlAddress(String urlAddress);
}
