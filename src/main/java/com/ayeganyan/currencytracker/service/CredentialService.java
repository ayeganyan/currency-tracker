package com.ayeganyan.currencytracker.service;

import com.ayeganyan.currencytracker.auth.UserPrincipal;
import com.ayeganyan.currencytracker.exception.DuplicateException;
import com.ayeganyan.currencytracker.model.AuthGroupEntity;
import com.ayeganyan.currencytracker.model.Credential;
import com.ayeganyan.currencytracker.model.CredentialEntity;
import com.ayeganyan.currencytracker.repository.AuthGroupRepository;
import com.ayeganyan.currencytracker.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ayeganyan.currencytracker.auth.SecurityProvider.getPasswordEncoder;
import static java.lang.String.format;

@Service
public class CredentialService implements UserDetailsService {

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private AuthGroupRepository authGroupRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CredentialEntity credentialEntity = credentialRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("Cannot find user with username %s", username)));
        List<AuthGroupEntity> authGroupEntities = authGroupRepository.findAllByCredentialEntity(credentialEntity).get();

        return new UserPrincipal(credentialEntity, authGroupEntities);
    }

    public Credential signup(Credential credential) {
        if(credentialRepository.findByUsername(credential.getUsername()).isPresent()) {
            throw new DuplicateException(format("User with username %s already exists", credential.getUsername()));
        }
        CredentialEntity credentialEntity = new CredentialEntity(credential.getUsername(),
                getPasswordEncoder().encode(credential.getPassword()));
        Credential savedCredential = Credential.from(credentialRepository.save(credentialEntity));
        authGroupRepository.save(new AuthGroupEntity("ROLE_USER", credentialEntity)); // TODO transaction

        return savedCredential;
    }
}
