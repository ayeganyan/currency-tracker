package com.ayeganyan.currencytracker.repository;

import com.ayeganyan.currencytracker.model.CurrencyRateEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CurrencyRateRepository extends CrudRepository<CurrencyRateEntity, Long> {

    Optional<CurrencyRateEntity> findTopByFromAndToOrderByTimestampDesc(String from, String to);

    Optional<List<CurrencyRateEntity>> findAllByTimestampAfterAndTimestampBeforeAndFromAndToOrderByTimestampAsc(
            Date fromDate, Date toDate, String fromCurrency, String toCurrency
    );
}
