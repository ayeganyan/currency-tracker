package com.ayeganyan.currencytracker.controller;

import com.ayeganyan.currencytracker.auth.JwtUtil;
import com.ayeganyan.currencytracker.model.Credential;
import com.ayeganyan.currencytracker.model.JwtToken;
import com.ayeganyan.currencytracker.service.CredentialService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    private CredentialService credentialService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;


    @ApiOperation(
            value = "Registers new credential",
            response = Credential.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "New credential has been successfully added"),
            @ApiResponse(code = 409, message = "Provided username is already in use")
    })
    @PostMapping("/v1/auth/signup")
    ResponseEntity<Credential> signup(
            @Valid
            @RequestBody Credential credential) {
        Credential savedCredential = credentialService.signup(credential);

        return new ResponseEntity<>(savedCredential, HttpStatus.CREATED);
    }

    @ApiOperation(
            value = "Generates Jwt Authentication token",
            response = JwtToken.class,
            produces = "application/json"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful generation of JWT token. Use token for Bearer Authentication"),
            @ApiResponse(code = 409, message = "Provided username is already in use")
    })
    @PostMapping("/v1/auth/authenticate")
    ResponseEntity<JwtToken> authenticate(@RequestBody Credential credential) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credential.getUsername(), credential.getPassword())
        );

        UserDetails userDetails = credentialService.loadUserByUsername(credential.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtToken(token));
    }
}
