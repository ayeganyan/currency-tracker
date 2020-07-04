package com.ayeganyan.currencytracker.service;

import com.ayeganyan.currencytracker.model.CurrencyRate;
import com.ayeganyan.currencytracker.model.CurrencyRateEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static com.ayeganyan.currencytracker.Constants.EUR;
import static com.ayeganyan.currencytracker.Constants.USD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations="classpath:application.properties")
@Transactional
@Sql("classpath:currency.sql")
public class CurrencyServiceTest {

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void addGetCurrencyRate() {
        currencyService.addCurrencyRate(USD, EUR, 2.0);

        CurrencyRate currencyRate = currencyService.getLatestCurrencyRate(USD, EUR);
        assertNotNull(currencyRate);
        assertEquals(USD, currencyRate.getFrom());
        assertEquals(EUR, currencyRate.getTo());
        assertEquals((Double) 2.0, currencyRate.getRate());
        assertNotNull(currencyRate.getTimestamp());
    }
}