package com.ayeganyan.currencytracker.repository;

import com.ayeganyan.currencytracker.model.CredentialEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CredentialRepository extends CrudRepository<CredentialEntity, Long> {
    Optional<CredentialEntity> findByUsername(String username);
}
