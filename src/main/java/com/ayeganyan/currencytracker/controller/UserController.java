package com.ayeganyan.currencytracker.controller;

import com.ayeganyan.currencytracker.model.User;
import com.ayeganyan.currencytracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/user/signup")
    ResponseEntity<User> signup(@RequestBody User user) {
        User savedUser = userService.signup(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
