package com.example.passwordmanagementsystem.services;

import com.example.passwordmanagementsystem.data.models.User;
import com.example.passwordmanagementsystem.data.repositories.UserRepository;
import com.example.passwordmanagementsystem.dtos.requests.RegisterUserRequest;
import com.example.passwordmanagementsystem.dtos.requests.UpdateUserRequest;
import com.example.passwordmanagementsystem.dtos.responses.RegisterUserResponse;
import com.example.passwordmanagementsystem.dtos.responses.UpdateUserResponse;
import com.example.passwordmanagementsystem.dtos.responses.UserDto;
import com.example.passwordmanagementsystem.exceptions.PasswordManagementException;
import com.example.passwordmanagementsystem.exceptions.UserExistException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    ModelMapper modelMapper = new ModelMapper();

    @Override
    public RegisterUserResponse registerUser( RegisterUserRequest registerUserRequest) {
        log.info("In here");
        Optional<User> found = userRepository.findUserBy(registerUserRequest.getEmail());
        if(found.isPresent()){
            throw new UserExistException("User "+registerUserRequest.getEmail()+" exists");
        }
        User user = new User();
        log.info("User has been created");
        modelMapper.map(registerUserRequest, user);
        user.setLoggedIn(true);

        User savedUser = userRepository.save(user);
        RegisterUserResponse response = new RegisterUserResponse();
        response.setEmail(savedUser.getEmail());
        log.info("Email is set");
        response.setUsername(savedUser.getUsername());
        response.setPhoneNumber(savedUser.getPhoneNumber());
        response.setMessage("Success");
        return response;
    }

    @Override
    public int count(){
        return (int) userRepository.count();
    }

    @Override
    public UserDto findUser(String id){
        User found = userRepository.findUserById(id).orElseThrow(() ->new PasswordManagementException("User not found"));
        ModelMapper mapper = new ModelMapper();
        return mapper.map(found, UserDto.class);
    }

    @Override
    public UpdateUserResponse updateUser(String id, UpdateUserRequest updateUserRequest) {
        User userToBeUpdated = userRepository.findUserById(id).orElseThrow(() -> new PasswordManagementException("Found no User with this id"));
        boolean updated = false;

        if(!(updateUserRequest.getPhoneNumber() == null || updateUserRequest.getPhoneNumber().trim().equals(""))){
            userToBeUpdated.setPhoneNumber(updateUserRequest.getPhoneNumber());
            updated = true;
        }

        if(!(updateUserRequest.getUsername() == null || updateUserRequest.getUsername().trim().equals(""))){
            userToBeUpdated.setUsername(updateUserRequest.getUsername());
            updated = true;
        }

        if(!(updateUserRequest.getEmail() == null || updateUserRequest.getEmail().trim().equals(""))){
            userToBeUpdated.setEmail(updateUserRequest.getEmail());
            updated = true;
        }

        if(!(updateUserRequest.getPhoneNumber() == null || updateUserRequest.getPhoneNumber().trim().equals(""))){
            userToBeUpdated.setPhoneNumber(updateUserRequest.getPhoneNumber());
            updated = true;
        }

        if(updated){
            userRepository.save(userToBeUpdated);
        }
        return UpdateUserResponse.builder()
                .username(updateUserRequest.getUsername())
                .emailAddress(updateUserRequest.getEmail())
                .phoneNumber(updateUserRequest.getPhoneNumber())
                .urlSet(userToBeUpdated.getUrls())
                .build();
    }

    @Override
    public Optional<User> findUserBy(String email) {
        return userRepository.findUserBy(email);
    }
}
