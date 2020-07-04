package com.ayeganyan.currencytracker.service;

import com.ayeganyan.currencytracker.exception.NotFoundException;
import com.ayeganyan.currencytracker.model.CurrencyRate;
import com.ayeganyan.currencytracker.model.CurrencyRateEntity;
import com.ayeganyan.currencytracker.repository.CurrencyRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    public CurrencyRate getLatestCurrencyRate(String from, String to) {

        CurrencyRateEntity latestRateEntity = currencyRateRepository.findTopByFromAndToOrderByTimestampDesc(from, to)
                .orElseThrow(() -> new NotFoundException(format("No data for currency rate %s:%s", from, to)));

        return CurrencyRate.from(latestRateEntity);
    }

    public CurrencyRate addCurrencyRate(String from, String to, Double rate) {
        CurrencyRateEntity currencyRateEntity = new CurrencyRateEntity();
        currencyRateEntity.setFrom(from);
        currencyRateEntity.setTo(to);
        currencyRateEntity.setRate(rate);

        return CurrencyRate.from(currencyRateRepository.save(currencyRateEntity));
    }
}
