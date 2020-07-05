package com.ayeganyan.currencytracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "auth_group_entity")
public class AuthGroupEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "auth_group")
    private String authGroup;

    @ManyToOne
    @JoinColumn(
            name = "user_fk",
            nullable = false
    )
    private CredentialEntity credentialEntity;

    public AuthGroupEntity() {
    }

    public AuthGroupEntity(String authGroup, CredentialEntity credentialEntity) {
        this.authGroup = authGroup;
        this.credentialEntity = credentialEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthGroup() {
        return authGroup;
    }

    public void setAuthGroup(String authGroup) {
        this.authGroup = authGroup;
    }

    public CredentialEntity getCredentialEntity() {
        return credentialEntity;
    }

    public void setCredentialEntity(CredentialEntity user) {
        this.credentialEntity = user;
    }
}
