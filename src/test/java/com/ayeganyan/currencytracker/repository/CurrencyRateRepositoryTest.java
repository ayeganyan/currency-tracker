package com.ayeganyan.currencytracker.repository;

import com.ayeganyan.currencytracker.exception.NotFoundException;
import com.ayeganyan.currencytracker.model.CurrencyRateEntity;
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
public class CurrencyRateRepositoryTest {

    @Autowired
    private CurrencyRateRepository currencyRateRepository;

    @Test
    public void getLatestRate() {
        CurrencyRateEntity currencyRateEntity = new CurrencyRateEntity();
        currencyRateEntity.setFrom(USD);
        currencyRateEntity.setTo(EUR);
        currencyRateEntity.setRate(2.0);

        CurrencyRateEntity latestSaved = currencyRateRepository.save(currencyRateEntity);
        Optional<CurrencyRateEntity> latestOpt = currencyRateRepository.findTopByFromAndToOrderByTimestampDesc(USD, EUR);
        CurrencyRateEntity latestGet = latestOpt.get();
        assertNotNull(latestGet);
        assertEquals(latestSaved.getId(), latestGet.getId());
    }

    @Test()
    public void getNonExistingCurrencyRate(){
        Optional<CurrencyRateEntity> latestRateOpt =
                currencyRateRepository.findTopByFromAndToOrderByTimestampDesc(CAD, EUR);
        assertFalse(latestRateOpt.isPresent());
    }

    @Test
    @Sql("classpath:range_query.sql")
    public void rangeQuery() throws ParseException {
        Date fromDate = DATE_FORMAT.parse("2020-07-04 14:19:58.033");
        Date toDate = DATE_FORMAT.parse("2020-07-04 14:53:58.033");
        Optional<List<CurrencyRateEntity>> ratePointsOpt = currencyRateRepository.
                findAllByTimestampAfterAndTimestampBeforeAndFromAndToOrderByTimestampAsc(fromDate, toDate, USD, EUR);
        List<CurrencyRateEntity> ratePoints = ratePointsOpt.get();
        assertNotNull(ratePoints);
        assertEquals(4, ratePoints.size());
    }
}