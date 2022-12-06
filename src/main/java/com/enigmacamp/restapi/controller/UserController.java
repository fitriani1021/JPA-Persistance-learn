package com.enigmacamp.restapi.controller;

import com.enigmacamp.restapi.model.response.PagingResponse;
import com.enigmacamp.restapi.model.response.SuccessResponse;
import com.enigmacamp.restapi.model.user.User;
import com.enigmacamp.restapi.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    
    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public ResponseEntity getUsers(){
        User[] users = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully Get All Users", users));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getUser(@PathVariable("id") String id){
        User user = userService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully Get User "+id, user));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") String id){
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
