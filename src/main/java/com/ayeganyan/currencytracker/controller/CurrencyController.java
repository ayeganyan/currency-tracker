package com.ayeganyan.currencytracker.controller;

import com.ayeganyan.currencytracker.model.CurrencyRate;
import com.ayeganyan.currencytracker.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class CurrencyController implements ICurrencyController{

    private final CurrencyService currencyService;

    public CurrencyController(@Autowired CurrencyService currencyService) {
        this.currencyService = Objects.requireNonNull(currencyService);
    }

    public ResponseEntity<CurrencyRate> getLatestCurrencyRate(
            String fromCurrency,
            String toCurrency
    ) {
        CurrencyRate currencyRate = currencyService.getLatestCurrencyRate(fromCurrency, toCurrency);

        return ResponseEntity.ok(currencyRate);
    }

    public ResponseEntity<List<CurrencyRate>> getCurrencyRateForRange(
            String fromCurrency,
            String toCurrency,
            Date fromDate,
            Date toDate
    ) {
        List<CurrencyRate> currencyRateForRange = currencyService
                .getCurrencyRateForRange(fromDate, toDate, fromCurrency, toCurrency);

        return ResponseEntity.ok(currencyRateForRange);
    }
    public ResponseEntity<CurrencyRate> addCurrencyRate(
            String from,
            String to,
            Double rate
    ) {
        CurrencyRate currencyRate = currencyService.addCurrencyRate(from, to, rate);

        return ResponseEntity.ok(currencyRate);
    }

}
