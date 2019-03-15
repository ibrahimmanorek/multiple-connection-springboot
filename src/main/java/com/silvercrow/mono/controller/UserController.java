package com.silvercrow.mono.controller;

import com.silvercrow.mono.user.model.User;
import com.silvercrow.mono.user.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public Optional<User> getUser(@PathVariable("id") Integer id){
        return userRepository.findById(id);
    }
}
