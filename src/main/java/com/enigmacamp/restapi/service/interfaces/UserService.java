package com.enigmacamp.restapi.service.interfaces;

import com.enigmacamp.restapi.model.user.User;

public interface UserService {
    User[] findAll();
    User findById(String id);
    void delete(String id);
}
