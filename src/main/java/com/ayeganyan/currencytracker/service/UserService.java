package com.ayeganyan.currencytracker.service;

import com.ayeganyan.currencytracker.auth.UserPrincipal;
import com.ayeganyan.currencytracker.exception.DuplicateException;
import com.ayeganyan.currencytracker.model.AuthGroupEntity;
import com.ayeganyan.currencytracker.model.User;
import com.ayeganyan.currencytracker.model.UserEntity;
import com.ayeganyan.currencytracker.repository.AuthGroupRepository;
import com.ayeganyan.currencytracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.ayeganyan.currencytracker.auth.SecurityProvider.getPasswordEncoder;
import static java.lang.String.format;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthGroupRepository authGroupRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(format("Cannot find user with username %s", username)));
        List<AuthGroupEntity> authGroupEntities = authGroupRepository.findAllByUserEntity(userEntity).get();

        return new UserPrincipal(userEntity, authGroupEntities);
    }

    public User signup(User user) {
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new DuplicateException(format("User with username %s already exists", user.getUsername()));
        }
        UserEntity userEntity = new UserEntity(user.getUsername(),
                getPasswordEncoder().encode(user.getPassword()));
        User savedUser = User.from(userRepository.save(userEntity));
        savedUser.setPassword(null);
        authGroupRepository.save(new AuthGroupEntity("ROLE_USER", userEntity)); // TODO transaction

        return savedUser;
    }
}
