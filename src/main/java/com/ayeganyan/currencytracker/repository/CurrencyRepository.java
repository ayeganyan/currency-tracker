package com.ayeganyan.currencytracker.repository;

import com.ayeganyan.currencytracker.model.CurrencyEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Long> {

    Optional<CurrencyEntity> findByCode(String code);

}
