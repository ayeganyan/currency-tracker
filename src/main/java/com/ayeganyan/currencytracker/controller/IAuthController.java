package com.ayeganyan.currencytracker.controller;

import com.ayeganyan.currencytracker.model.Credential;
import com.ayeganyan.currencytracker.model.JwtToken;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public interface IAuthController {

    @ApiOperation(
            value = "Registers new credential",
            response = Credential.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New credential has been successfully added"),
            @ApiResponse(code = 409, message = "Provided username is already in use")
    })
    @PostMapping("/signup")
    ResponseEntity<Credential> signup(
            @Valid
            @RequestBody Credential credential);

    @ApiOperation(
            value = "Generates Jwt Authentication token",
            response = JwtToken.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful generation of JWT token. Use token for Bearer Authentication"),
            @ApiResponse(code = 409, message = "Provided username is already in use")
    })
    @PostMapping("/authenticate")
    ResponseEntity<JwtToken> authenticate(@RequestBody Credential credential);
}
