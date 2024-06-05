package com.osu.venglar.EventHarbor.controller;

import com.osu.venglar.EventHarbor.auth.*;
import com.osu.venglar.EventHarbor.model.User;
import com.osu.venglar.EventHarbor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationService service;
    @GetMapping("/api/v1/users")
    List<User> users(){return userRepository.findAll();}

    @GetMapping("/api/v1/user/{id}")
    User userFromDB(@PathVariable Long id){return userRepository.findByUserId(id);}

    @DeleteMapping("/api/v1/user/{id}")
    Integer deleteUser(@PathVariable Long id){return userRepository.deleteByUserId(id);}

    @PutMapping("/api/v1/user/{id}")
    public ResponseEntity<String> updateUser(
            @RequestBody UpdateRequest updateRequest, @PathVariable Long id
    ){
     return service.update(updateRequest,id);
    }
}


