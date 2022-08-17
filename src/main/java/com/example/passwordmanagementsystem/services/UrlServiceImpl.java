package com.example.passwordmanagementsystem.services;

import com.example.passwordmanagementsystem.data.models.Url;
import com.example.passwordmanagementsystem.data.models.User;
import com.example.passwordmanagementsystem.data.repositories.UrlRepository;
import com.example.passwordmanagementsystem.data.repositories.UserRepository;
import com.example.passwordmanagementsystem.dtos.requests.AddUrlRequest;
import com.example.passwordmanagementsystem.dtos.requests.UpdateUrlRequest;
import com.example.passwordmanagementsystem.dtos.responses.DeleteUrlResponse;
import com.example.passwordmanagementsystem.dtos.responses.UpdateUrlResponse;
import com.example.passwordmanagementsystem.dtos.responses.UrlDto;
import com.example.passwordmanagementsystem.exceptions.PasswordManagementException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UrlServiceImpl implements UrlService{
    @Autowired
    private UrlRepository urlRepository;
    @Autowired
    private UserService userService;
    private UserRepository userRepository;

    @Override
    public UrlDto addUrl(AddUrlRequest request){
        Optional<User> user = userService.findUserBy(request.getEmail());
        if(user.isEmpty()){
            throw  new PasswordManagementException("User with email address "+request.getEmail()+" does not exist");
        }
        Url url = Url.builder()
                .urlAddress(request.getUrlAddress())
                .urlEmail(request.getEmail())
                .urlPassword(request.getPassword())
                .username(request.getUsername())
                .build();

        Url savedUrl = urlRepository.save(url);
        user.get().getUrls().add(savedUrl);

        ModelMapper mapper = new ModelMapper();
        UrlDto urlDto = mapper.map(url, UrlDto.class);
        urlDto.setMessage("Url added successfully");
        return urlDto;
    }

    @Override
    public UrlDto findUrl(String urlAddress) {
        Url url = urlRepository.findByUrlAddress(urlAddress);
        if(url == null){ throw new PasswordManagementException("Url Address does not exist in db");}
        ModelMapper mapper = new ModelMapper();
        return mapper.map(url, UrlDto.class);
    }

    @Override
    public UpdateUrlResponse updateUrl(String email, UpdateUrlRequest request) {
        User user = userService.findUserBy(email).orElseThrow(() -> new PasswordManagementException("Email does not exist"));
        Set <Url> urls = user.getUrls();

        UpdateUrlResponse response = new UpdateUrlResponse();
        urls.forEach(url -> {
            boolean isUpdated = false;
            if (url.getUrlAddress().equals(request.getUrlAddress())) {
                if (!(request.getPassword() == null || request.getPassword().trim().equals(""))) {
                    url.setUrlPassword(request.getPassword());
                    isUpdated = true;
                }
                if (!(request.getEmail() == null || request.getEmail().trim().equals(""))) {
                    url.setUrlEmail(request.getEmail());
                    isUpdated = true;
                }
                if (isUpdated) {
                    url = urlRepository.save(url);
                    user.getUrls().add(url);
                    response.setUrl(url);
                    userRepository.save(user);
                }
            }
        });

        response.setUpdated(true);

        return response;
    }

    @Override
    public DeleteUrlResponse deleteUrl(String urlAddress) {
        Url url = urlRepository.findByUrlAddress(urlAddress);
        if(url == null){
            throw new PasswordManagementException("Url not found");
        }
        urlRepository.delete(url);
        DeleteUrlResponse response = new DeleteUrlResponse();
        response.setMessage(urlAddress+ " deleted");
        response.setUrl(url);
        return response;
    }


}
