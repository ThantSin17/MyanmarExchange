package com.example.myanmarexchange.dto;

public class Country {
    String cur,country;

    public Country(String cur, String country) {
        this.cur = cur;
        this.country = country;
    }

    public String getCur() {
        return cur;
    }

    public void setCur(String cur) {
        this.cur = cur;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
