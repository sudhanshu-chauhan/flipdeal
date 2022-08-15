package com.flipdeal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

class Discount {

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAmountTag() {
        return this.amountTag;
    }

    public void setAmountTag(String amountTag) {
        this.amountTag = amountTag;
    }

    private double amount;
    private String amountTag;
}

@JsonInclude(Include.NON_NULL)
public class ProductResponse {
    private String category;
    private Integer inventory;
    private Double rating;
    private String currency;
    private Double price;
    private String origin;
    private String product;
    private String arrival;

    public String getArrival() {
        return this.arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }
    private Discount discount;

    public Discount getDiscount() {
        return this.discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

   
    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getInventory() {
        return this.inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Double getRating() {
        return this.rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getPrice() {
        return this.price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getOrigin() {
        return this.origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }
}
