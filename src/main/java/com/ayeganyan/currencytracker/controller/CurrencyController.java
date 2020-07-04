package com.ayeganyan.currencytracker.controller;

import com.ayeganyan.currencytracker.model.CurrencyRate;
import com.ayeganyan.currencytracker.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/v1/currency/from/{fromCurrency}/to/{toCurrency}/latest")
    public ResponseEntity<CurrencyRate> getLatestCurrencyRate(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency
    ) {
        CurrencyRate currencyRate = currencyService.getLatestCurrencyRate(fromCurrency, toCurrency);

        return ResponseEntity.ok(currencyRate);
    }

    @GetMapping("/v1/currency/from/{fromCurrency}/to/{toCurrency}/range")
    public ResponseEntity<List<CurrencyRate>> getCurrencyRateForRange(
            @PathVariable String fromCurrency,
            @PathVariable String toCurrency,
            @RequestParam Date fromDate,
            @RequestParam Date toDate
    ) {

        List<CurrencyRate> currencyRateForRange = currencyService
                .getCurrencyRateForRange(fromDate, toDate, fromCurrency, toCurrency);

        return ResponseEntity.ok(currencyRateForRange);
    }

    @PutMapping("/v1/currency/from/{from}/to/{to}")
    public ResponseEntity<Object> addCurrencyRate(
            @PathVariable String from,
            @PathVariable String to,
            @RequestParam Double rate
    ) {
        CurrencyRate currencyRate = currencyService.addCurrencyRate(from, to, rate);

        return ResponseEntity.ok(currencyRate);
    }

}
