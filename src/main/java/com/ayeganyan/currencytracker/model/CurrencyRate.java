package com.ayeganyan.currencytracker.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

@ApiModel(description = "Represents model for currency rate")
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
    @ApiModelProperty(notes = "Code of currency to be converted")
    private String from;

    @NotNull
    @Size(min = 3, max = 3, message = "Currency code should have size of 3")
    @ApiModelProperty(notes = "Code of currency to convert")
    private String to;

    @NotNull
    @ApiModelProperty(notes = "Actual currency rate")
    private Double rate;

    @PastOrPresent
    @ApiModelProperty(notes = "Timestamp of currency rate calculation")
    private Date timestamp;

    public CurrencyRate(String from, String to, double rate, Date timestamp) {
        this.from = from;
        this.to = to;
        this.rate = rate;
        this.timestamp = timestamp;
    }

    public CurrencyRate() {
    }

    @ApiModelProperty(
            value = "Code of base currency of conversion",
            allowableValues = "USD,EUR,GBP",
            example = "USD",
            required = true
    )
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    @ApiModelProperty(
            value = "Code of currency to convert",
            allowableValues = "USD,EUR,GBP",
            example = "EUR",
            required = true
    )
    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @ApiModelProperty(
            value = "Conversion rate",
            example = "1.2",
            required = true
    )
    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @ApiModelProperty(
            value = "Actual time of conversion calculation"
    )
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
