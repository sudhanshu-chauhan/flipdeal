package com.flipdeal;
import java.util.HashMap;

public class ExchangeRateResponse {
    private String base;

    public String getBase() {
        return this.base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap<String,Double> getRates() {
        return this.rates;
    }

    public void setRates(HashMap<String,Double> rates) {
        this.rates = rates;
    }

    public Boolean isSuccess() {
        return this.success;
    }

    public Boolean getSuccess() {
        return this.success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Double getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Double timestamp) {
        this.timestamp = timestamp;
    }
    private String date;
    private HashMap<String,Double> rates;
    private Boolean success;
    private Double timestamp;
}
