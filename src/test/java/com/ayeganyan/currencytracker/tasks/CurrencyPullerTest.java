package com.ayeganyan.currencytracker.tasks;

import com.ayeganyan.currencytracker.model.CurrencyRate;
import com.ayeganyan.currencytracker.service.CurrencyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import static com.ayeganyan.currencytracker.Constants.EUR;
import static com.ayeganyan.currencytracker.Constants.USD;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestPropertySource(locations="classpath:application.properties")
@Transactional
@Sql("classpath:currency.sql")
public class CurrencyPullerTest {

    @Autowired
    private CurrencyPuller currencyPuller;

    @Autowired
    private CurrencyService currencyService;

    @Test
    public void pullRate() {

        Double rate = 1.4;

        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setBaseCode(EUR);
        exchangeRate.setSuccess("true");
        exchangeRate.setLastUpdate(Calendar.getInstance().getTime());
        Map<String, Double> rates = new HashMap<>();
        rates.put(USD, rate);
        exchangeRate.setRates(rates);

        RestTemplate mockedRestTemplate = mock(RestTemplate.class);
        when(mockedRestTemplate.getForObject(any(String.class), any(Class.class)))
                .thenReturn(exchangeRate);
        currencyPuller.setRestTemplate(mockedRestTemplate);

        currencyPuller.pullRate();

        CurrencyRate usdEurRate = currencyService.getLatestCurrencyRate(EUR, USD);
        Assert.assertNotNull(usdEurRate);
        Assert.assertEquals(rate, usdEurRate.getRate());

        CurrencyRate eurUsdRate = currencyService.getLatestCurrencyRate(USD, EUR);
        Assert.assertNotNull(eurUsdRate);
        Assert.assertEquals((Double) (1.0 / rate), eurUsdRate.getRate());
    }
}