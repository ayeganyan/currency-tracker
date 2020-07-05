package com.ayeganyan.currencytracker.repository;

import com.ayeganyan.currencytracker.model.AuthGroupEntity;
import com.ayeganyan.currencytracker.model.CredentialEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthGroupRepository extends JpaRepository<AuthGroupEntity, Long> {
    Optional<List<AuthGroupEntity>> findAllByCredentialEntity(CredentialEntity credentialEntity);
}
