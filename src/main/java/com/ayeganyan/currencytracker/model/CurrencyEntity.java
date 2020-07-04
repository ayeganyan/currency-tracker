package com.ayeganyan.currencytracker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "CurrencyEntity")
public class CurrencyEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "code",
            length = 3,
            unique = true,
            nullable = false
    )
    private String code;

    public CurrencyEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
