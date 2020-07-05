package com.ayeganyan.currencytracker.controller;

import com.ayeganyan.currencytracker.model.User;
import com.ayeganyan.currencytracker.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(
            value = "Registers new user",
            response = User.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New user has been successfully added"),
            @ApiResponse(code = 409, message = "Provided username is aleardy in use")
    })
    @PostMapping("/v1/user/signup")
    ResponseEntity<User> signup(
            @Valid
            @RequestBody User user) {
        User savedUser = userService.signup(user);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
