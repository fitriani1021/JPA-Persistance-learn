package com.enigmacamp.restapi.repository.interfaces;

import com.enigmacamp.restapi.model.user.User;

public interface UserRepository {
    User[] getAll();
    User getById(String id);
    void delete(String id);
}
