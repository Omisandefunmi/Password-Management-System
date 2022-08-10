package com.example.passwordmanagementsystem.services;

import com.example.passwordmanagementsystem.data.models.User;
import com.example.passwordmanagementsystem.data.repositories.UserRepository;
import com.example.passwordmanagementsystem.dtos.requests.RegisterUserRequest;
import com.example.passwordmanagementsystem.dtos.responses.RegisterUserResponse;
import com.example.passwordmanagementsystem.exceptions.UserExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest) {
        Optional<User> found = userRepository.findUserBy(registerUserRequest.getEmail());
        if(found.isPresent()){
            throw new UserExistException("User "+registerUserRequest.getEmail()+" exists");
        }
        User user = new User();
        modelMapper.map(registerUserRequest, user);
        user.setLoggedIn(true);

        userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setMessage("Success");
        return response;
    }

    @Override
    public int count(){
        return (int) userRepository.count();
    }

}
