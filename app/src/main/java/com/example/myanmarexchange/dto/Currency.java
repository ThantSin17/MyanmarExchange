package com.example.myanmarexchange.dto;

import java.io.Serializable;

public class Currency implements Serializable {
    private String countryName,currency,rate;
    private String buyRate,sellRate;
    public Currency(String countryName, String currency, String rate) {
        this.countryName = countryName;
        this.currency = currency;
        this.rate = rate;
    }

    public Currency(String countryName, String currency, String buyRate, String sellRate) {
        this.countryName = countryName;
        this.currency = currency;
        this.buyRate = buyRate;
        this.sellRate = sellRate;
    }

    public String getBuyRate() {
        return buyRate;
    }

    public void setBuyRate(String buyRate) {
        this.buyRate = buyRate;
    }

    public String getSellRate() {
        return sellRate;
    }

    public void setSellRate(String sellRate) {
        this.sellRate = sellRate;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
}
