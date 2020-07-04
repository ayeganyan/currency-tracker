package com.ayeganyan.currencytracker.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

public class CurrencyRate {

    public static CurrencyRate from(CurrencyRateEntity entity) {
        return new CurrencyRate(
                entity.getFromCurrency().getCode(),
                entity.getToCurrency().getCode(),
                entity.getRate(),
                entity.getTimestamp()
        );
    }

    @NotNull
    @Size(min = 3, max = 3, message = "Currency code should have size of 3")
    private String from;

    @NotNull
    @Size(min = 3, max = 3, message = "Currency code should have size of 3")
    private String to;

    @NotNull
    private Double rate;

    @PastOrPresent
    private Date timestamp;

    public CurrencyRate(String from, String to, double rate, Date timestamp) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public CurrencyRate() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
