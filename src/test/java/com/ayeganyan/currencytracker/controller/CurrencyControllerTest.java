package com.ayeganyan.currencytracker.controller;

import com.ayeganyan.currencytracker.exception.BadRequestException;
import com.ayeganyan.currencytracker.exception.NotFoundException;
import com.ayeganyan.currencytracker.model.CurrencyRate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static com.ayeganyan.currencytracker.Constants.CAD;
import static com.ayeganyan.currencytracker.Constants.DATE_FORMAT;
import static com.ayeganyan.currencytracker.Constants.EUR;
import static com.ayeganyan.currencytracker.Constants.USD;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations="classpath:application.properties")
@Transactional
@Sql("classpath:currency.sql")
public class CurrencyControllerTest {

    @Autowired
    private CurrencyController currencyController;

    @Test
    public void getAddLatestCurrencyRate() {
        currencyController.addCurrencyRate(USD, EUR, 2.0);

        ResponseEntity<CurrencyRate> currencyRateResponse = currencyController.getLatestCurrencyRate(USD, EUR);

        assertNotNull(currencyRateResponse);
        assertEquals(HttpStatus.OK, currencyRateResponse.getStatusCode());
        CurrencyRate body = currencyRateResponse.getBody();
        assertNotNull(body);
        assertEquals((Double)2.0, body.getRate());
        assertEquals(USD, body.getFrom());
        assertEquals(EUR, body.getTo());
    }

    @Test(expected = BadRequestException.class)
    public void getLatestNonExistingRate() {
        currencyController.getLatestCurrencyRate(CAD, USD);
    }

    @Test
    public void getCurrencyRateForRange() throws ParseException {
        Date fromDate = DATE_FORMAT.parse("2020-07-04 14:19:58.033");
        Date toDate = DATE_FORMAT.parse("2020-07-04 14:53:58.033");
        ResponseEntity<List<CurrencyRate>> currencyRateRangeResponse = currencyController
                .getCurrencyRateForRange(USD, EUR, fromDate, toDate);

        assertNotNull(currencyRateRangeResponse);
        assertEquals(HttpStatus.OK, currencyRateRangeResponse.getStatusCode());
        List<CurrencyRate> body = currencyRateRangeResponse.getBody();
        assertNotNull(body);
        assertEquals(4, body.size());
    }
}