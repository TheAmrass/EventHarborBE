package com.osu.venglar.EventHarbor.controller;

import com.osu.venglar.EventHarbor.auth.*;
import com.osu.venglar.EventHarbor.exception.UserNotFoundException;
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
//Connection with FE
@CrossOrigin("http://localhost:3000")
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
    Integer deleteUserRequest(@PathVariable Long id){return userRepository.deleteByUserId(id);}

    @PutMapping("/api/v1/user/{id}")
    public ResponseEntity<String> updateUser(
            @RequestBody UpdateRequest updateRequest, @PathVariable Long id
    ){
     return service.update(updateRequest,id);
    }

    @GetMapping("/users")
    List<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
        return userRepository.findById(id)
                .orElseThrow(()->new UserNotFoundException(id));
    }

    @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setPassword(newUser.getPassword());
                    user.setRole(newUser.getRole());
                    user.setEmail(newUser.getEmail());
                    return userRepository.save(user);
                }).orElseThrow(()->new UserNotFoundException(id));
    }

    //Tohle dělám kvůli gitu, můžu tenhle řádek pak smazat :)))

    @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
        return "Uzivatel s id "+id+"byl uspesne odstranen.";
    }

    //Zkouším git <3

}
