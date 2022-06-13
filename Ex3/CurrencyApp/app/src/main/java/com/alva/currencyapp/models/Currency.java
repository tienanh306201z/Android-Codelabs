package com.alva.currencyapp.models;

public class Currency {
    private String title;
    private String moneySign;

    private String moneyStandFor;

    private double exchangeRate;

    public Currency(String title, String moneyStandFor, String moneySign, double exchangeRate) {
        this.title = title;
        this.moneyStandFor = moneyStandFor;
        this.moneySign = moneySign;
        this.exchangeRate = exchangeRate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoneyStandFor() {
        return moneyStandFor;
    }

    public void setMoneyStandFor(String moneyStandFor) {
        this.moneyStandFor = moneyStandFor;
    }

    public String getMoneySign() {
        return moneySign;
    }

    public void setMoneySign(String moneySign) {
        this.moneySign = moneySign;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
