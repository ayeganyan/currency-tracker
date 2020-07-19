package com.ayeganyan.currencytracker.controller;

import com.ayeganyan.currencytracker.auth.JwtUtil;
import com.ayeganyan.currencytracker.model.Credential;
import com.ayeganyan.currencytracker.model.JwtToken;
import com.ayeganyan.currencytracker.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthController implements IAuthController{

    private final CredentialService credentialService;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    public AuthController(@Autowired CredentialService credentialService,
                          @Autowired AuthenticationManager authenticationManager,
                          @Autowired JwtUtil jwtUtil) {
        this.credentialService = Objects.requireNonNull(credentialService);
        this.authenticationManager = Objects.requireNonNull(authenticationManager);
        this.jwtUtil = Objects.requireNonNull(jwtUtil);
    }

    @Override
    public ResponseEntity<Credential> signup(Credential credential) {
        Credential savedCredential = credentialService.signup(credential);

        return new ResponseEntity<>(savedCredential, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<JwtToken> authenticate(Credential credential) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credential.getUsername(), credential.getPassword())
        );

        UserDetails userDetails = credentialService.loadUserByUsername(credential.getUsername());
        String token = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtToken(token));
    }
}
