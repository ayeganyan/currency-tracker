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

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/v1/currency/from/{from}/to/{to}/latest")
    public ResponseEntity<CurrencyRate> getCurrencyRate(@PathVariable String from, @PathVariable String to) {

        CurrencyRate currencyRate = currencyService.getLatestCurrencyRate(from, to);

        return ResponseEntity.ok(currencyRate);
    }

    @PutMapping("/v1/currency/from/{from}/to/{to}")
    public ResponseEntity<Object> addCurrencyRate(@PathVariable String from, @PathVariable String to, @RequestParam Double rate) {

        CurrencyRate currencyRate = currencyService.addCurrencyRate(from, to, rate);

        return ResponseEntity.ok(currencyRate);
    }

}
