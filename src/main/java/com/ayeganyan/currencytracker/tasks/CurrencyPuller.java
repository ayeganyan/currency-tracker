package com.ayeganyan.currencytracker.tasks;

import com.ayeganyan.currencytracker.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class CurrencyPuller {

    @Value("${puller.accesstoken}")
    private String fixerToken;

    // The logic was not generalized because of API restriction, only EUR base allowed
    private final String base = "EUR";
    private final String interestedCurrency = "USD";

    private RestTemplate restTemplate = new RestTemplate();

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private CurrencyService currencyService;

    @Scheduled(fixedRate = 5000L)
    void pullRate(){
        ExchangeRate exchangeRate = restTemplate.getForObject(
                String.format("http://data.fixer.io/api/latest?access_key=%s&symbols=%s&base=%s",
                        fixerToken, interestedCurrency, base),
                ExchangeRate.class);
        if(exchangeRate == null || !exchangeRate.getSuccess().equals("true")) {
            return;
        }
        Map<String, Double> rates = exchangeRate.getRates();
        Double rate = rates.get(interestedCurrency);
        if(rate == null) {
            return;
        }
        currencyService.addCurrencyRate(exchangeRate.getBaseCode(), interestedCurrency, rate);
        currencyService.addCurrencyRate(interestedCurrency, exchangeRate.getBaseCode(), 1.0 / rate);
    }

}
