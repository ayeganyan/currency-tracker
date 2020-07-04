package com.ayeganyan.currencytracker.repository;

import com.ayeganyan.currencytracker.model.CurrencyEntity;
import com.ayeganyan.currencytracker.model.CurrencyRateEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.ayeganyan.currencytracker.Constants.DATE_FORMAT;
import static com.ayeganyan.currencytracker.Constants.EUR;
import static com.ayeganyan.currencytracker.Constants.USD;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations="classpath:application.properties")
@Transactional
@Sql("classpath:currency.sql")
public class CurrencyRateRepositoryTest {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    private CurrencyEntity usdEntity;
    private CurrencyEntity eurEntity;

    @Before
    public void setUp() throws Exception {
        Optional<CurrencyEntity> usdEntityOpt = currencyRepository.findByCode(USD);
        assertTrue(usdEntityOpt.isPresent());
        usdEntity = usdEntityOpt.get();

        Optional<CurrencyEntity> eurEntityOpt = currencyRepository.findByCode(EUR);
        assertTrue(eurEntityOpt.isPresent());
        eurEntity = eurEntityOpt.get();
    }

    @Test
    public void getLatestRate() {
        CurrencyRateEntity currencyRateEntity = new CurrencyRateEntity();
        currencyRateEntity.setRate(2.0);
        currencyRateEntity.setFromCurrency(usdEntity);
        currencyRateEntity.setToCurrency(eurEntity);
        CurrencyRateEntity latestSaved = currencyRateRepository.save(currencyRateEntity);

        Optional<CurrencyRateEntity> latestOpt = currencyRateRepository
                .findTopByFromCurrencyAndToCurrencyOrderByTimestampDesc(usdEntity, eurEntity);
        assertTrue(latestOpt.isPresent());
        CurrencyRateEntity latestGet = latestOpt.get();
        assertNotNull(latestGet);
        assertEquals(latestSaved.getId(), latestGet.getId());
    }

    @Test
    public void rangeQuery() throws ParseException {
        Date fromDate = DATE_FORMAT.parse("2020-07-04 14:19:58.033");
        Date toDate = DATE_FORMAT.parse("2020-07-04 14:53:58.033");
        Optional<List<CurrencyRateEntity>> ratePointsOpt = currencyRateRepository.
                findAllByTimestampAfterAndTimestampBeforeAndFromCurrencyAndToCurrencyOrderByTimestampAsc(
                        fromDate, toDate, usdEntity, eurEntity);
        assertTrue(ratePointsOpt.isPresent());
        List<CurrencyRateEntity> ratePoints = ratePointsOpt.get();
        assertNotNull(ratePoints);
        assertEquals(4, ratePoints.size());
    }
}