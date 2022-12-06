package com.enigmacamp.restapi.repository.implementation;

import com.enigmacamp.restapi.exception.RestTemplateException;
import com.enigmacamp.restapi.model.user.User;
import com.enigmacamp.restapi.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserRepositoryImpl implements UserRepository {
    @Value("${service.userUri}")
    private String serviceUserUri;
    private RestTemplate restTemplate;
    
    public UserRepositoryImpl(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    @Override
    public User[] getAll() {
        try {
            ResponseEntity<User[]> response = restTemplate.getForEntity(serviceUserUri, User[].class);
            return response.getBody();
        }catch(RestClientException e) {
            throw new RestTemplateException(serviceUserUri, HttpStatus.UNPROCESSABLE_ENTITY,"service is down");
        }
    }
    
    @Override
    public User getById(String id) {
        try{
            ResponseEntity<User> response = restTemplate.getForEntity(serviceUserUri+"/"+id, User.class);
            return response.getBody();
        }catch (Exception e){
            throw new RestTemplateException(serviceUserUri, HttpStatus.UNPROCESSABLE_ENTITY,"service is down");
        }
    }
    
    @Override
    public void delete(String id) {
        restTemplate.delete(serviceUserUri+"/"+id);
    }
}
