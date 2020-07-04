package com.ayeganyan.currencytracker.model;

import java.util.Date;

public class CurrencyRate {

    public static CurrencyRate from(CurrencyRateEntity entity) {
        return new CurrencyRate(entity.getFrom(), entity.getTo(), entity.getRate(), entity.getTimestamp());
    }

    private String from;

    private String to;

    private Double rate;

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
