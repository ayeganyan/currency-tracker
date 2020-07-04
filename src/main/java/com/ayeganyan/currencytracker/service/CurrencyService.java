package com.ayeganyan.currencytracker.service;

import com.ayeganyan.currencytracker.exception.NotFoundException;
import com.ayeganyan.currencytracker.model.CurrencyEntity;
import com.ayeganyan.currencytracker.model.CurrencyRate;
import com.ayeganyan.currencytracker.model.CurrencyRateEntity;
import com.ayeganyan.currencytracker.repository.CurrencyRateRepository;
import com.ayeganyan.currencytracker.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    public CurrencyRate getLatestCurrencyRate(String fromCurrency, String toCurrency) {

        CurrencyEntity fromCurrencyEntity = retrieveCurrencyEntity(fromCurrency);
        CurrencyEntity toCurrencyEntity = retrieveCurrencyEntity(toCurrency);

        CurrencyRateEntity latestRateEntity = currencyRateRepository
                .findTopByFromCurrencyAndToCurrencyOrderByTimestampDesc(fromCurrencyEntity, toCurrencyEntity)
                .orElseThrow(() -> new NotFoundException(format("No data for currency rate %s:%s", fromCurrency, toCurrency)));

        return CurrencyRate.from(latestRateEntity);
    }

    private CurrencyEntity retrieveCurrencyEntity(String code) {
        return currencyRepository.findByCode(code)
                .orElseThrow(() -> new NotFoundException(format("can not find currency with code %s", code)));
    }

    public CurrencyRate addCurrencyRate(String fromCurrency, String toCurrency, Double rate) {
        CurrencyRateEntity currencyRateEntity = new CurrencyRateEntity();

        currencyRateEntity.setFromCurrency(retrieveCurrencyEntity(fromCurrency));
        currencyRateEntity.setToCurrency(retrieveCurrencyEntity(toCurrency));
        currencyRateEntity.setRate(rate);

        return CurrencyRate.from(currencyRateRepository.save(currencyRateEntity));
    }

    public List<CurrencyRate> getCurrencyRateForRange(Date fromDate, Date toDate, String fromCurrency, String toCurrency) {

        CurrencyEntity fromCurrencyEntity = retrieveCurrencyEntity(fromCurrency);
        CurrencyEntity toCurrencyEntity = retrieveCurrencyEntity(toCurrency);

        List<CurrencyRateEntity> currencyRateEntityRange = currencyRateRepository.
                findAllByTimestampAfterAndTimestampBeforeAndFromCurrencyAndToCurrencyOrderByTimestampAsc(
                fromDate, toDate, fromCurrencyEntity, toCurrencyEntity
        ).orElseThrow(() -> new NotFoundException(
                format("No data for currency rate %s:%s for time range [%s -> %s]",
                        fromCurrency, toCurrency, fromDate.toString(), toDate.toString())));

        return currencyRateEntityRange.stream()
                .map(CurrencyRate::from)
                .collect(toList());
    }
}
