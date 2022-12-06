package com.enigmacamp.restapi.service.implementation;

import com.enigmacamp.restapi.model.user.User;
import com.enigmacamp.restapi.repository.interfaces.UserRepository;
import com.enigmacamp.restapi.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    
    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User[] findAll() {
        return userRepository.getAll();
    }
    
    @Override
    public User findById(String id) {
        return userRepository.getById(id);
    }
    
    @Override
    public void delete(String id) {
        userRepository.delete(id);
    }
}
