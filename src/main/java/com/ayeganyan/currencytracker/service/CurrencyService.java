package com.ayeganyan.currencytracker.service;

import com.ayeganyan.currencytracker.exception.NotFoundException;
import com.ayeganyan.currencytracker.model.CurrencyRate;
import com.ayeganyan.currencytracker.model.CurrencyRateEntity;
import com.ayeganyan.currencytracker.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    public CurrencyRate getLatestCurrencyRate(String fromCurrency, String toCurrency) {

        CurrencyRateEntity latestRateEntity = currencyRateRepository
                .findTopByFromAndToOrderByTimestampDesc(fromCurrency, toCurrency)
                .orElseThrow(() -> new NotFoundException(format("No data for currency rate %s:%s", fromCurrency, toCurrency)));

        return CurrencyRate.from(latestRateEntity);
    }

    public CurrencyRate addCurrencyRate(String fromCurrency, String toCurrency, Double rate) {
        CurrencyRateEntity currencyRateEntity = new CurrencyRateEntity();
        currencyRateEntity.setFrom(fromCurrency);
        currencyRateEntity.setTo(toCurrency);
        currencyRateEntity.setRate(rate);

        return CurrencyRate.from(currencyRateRepository.save(currencyRateEntity));
    }

    public List<CurrencyRate> getCurrencyRateForRange(Date fromDate, Date toDate, String fromCurrency, String toCurrency) {
        List<CurrencyRateEntity> currencyRateEntityRange = currencyRateRepository.
                findAllByTimestampAfterAndTimestampBeforeAndFromAndToOrderByTimestampAsc(
                fromDate, toDate, fromCurrency, toCurrency
        ).orElseThrow(() -> new NotFoundException(
                format("No data for currency rate %s:%s for time range [%s -> %s]",
                        fromCurrency, toCurrency, fromDate.toString(), toDate.toString())));

        return currencyRateEntityRange.stream()
                .map(CurrencyRate::from)
                .collect(toList());
    }
}
