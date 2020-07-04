package com.ayeganyan.currencytracker.repository;

import com.ayeganyan.currencytracker.model.CurrencyRateEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CurrencyRateRepository extends CrudRepository<CurrencyRateEntity, Long> {

    Optional<CurrencyRateEntity> findTopByFromAndToOrderByTimestampDesc(String from, String to);
}
