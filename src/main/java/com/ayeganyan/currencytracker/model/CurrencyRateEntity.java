package com.ayeganyan.currencytracker.model;


import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

@Entity
@Table( name = "currency_rate_entity",
        indexes ={@Index(name = "timestamp_index", columnList = "timestamp", unique = false)})
public class CurrencyRateEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic(optional = false)
    @Column(name = "timestamp", updatable = false)
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @PastOrPresent
    private Date timestamp;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "from_currency_fk")
    private CurrencyEntity fromCurrency;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "to_currency_fk")
    private CurrencyEntity toCurrency;

    @Column(name = "rate")
    @NotNull
    private Double rate;

    public CurrencyRateEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public CurrencyEntity getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(CurrencyEntity fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public CurrencyEntity getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(CurrencyEntity to) {
        this.toCurrency = to;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
