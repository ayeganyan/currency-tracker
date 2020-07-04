package com.ayeganyan.currencytracker.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static com.ayeganyan.currencytracker.Constants.CAD;
import static com.ayeganyan.currencytracker.Constants.USD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations="classpath:application.properties")
@Transactional
@Sql("classpath:currency.sql")
public class CurrencyRepositoryTest {

    @Autowired
    private CurrencyRepository currencyRepository;

    @Test
    public void getByCode() {
        assertTrue(currencyRepository.findByCode(USD).isPresent());
    }

    @Test
    public void getNonExistingByCode() {
        assertFalse(currencyRepository.findByCode(CAD).isPresent());
    }
}