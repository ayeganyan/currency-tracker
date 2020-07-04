package com.ayeganyan.currencytracker.repository;

import com.ayeganyan.currencytracker.model.CurrencyEntity;
import com.ayeganyan.currencytracker.model.CurrencyRateEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface CurrencyRateRepository extends CrudRepository<CurrencyRateEntity, Long> {

    Optional<CurrencyRateEntity> findTopByFromCurrencyAndToCurrencyOrderByTimestampDesc(CurrencyEntity from, CurrencyEntity to);

    Optional<List<CurrencyRateEntity>> findAllByTimestampAfterAndTimestampBeforeAndFromCurrencyAndToCurrencyOrderByTimestampAsc(
            Date fromDate, Date toDate, CurrencyEntity fromCurrency, CurrencyEntity toCurrency
    );
}
