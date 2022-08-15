package com.flipdeal;

import java.io.InputStream;
import java.util.HashMap;
import java.nio.file.Paths;
import java.util.logging.*;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ProductFactory {
    protected final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    protected final String productURL = "https://mock.coverself.net/rest/hiring/products";
    protected final String exchangeRateURL = "https://mock.coverself.net/rest/hiring/exchange-rates";
    protected ProductResponse[] products;
    protected HashMap<String, Double> exchangeRate;
    
    protected final String discountedProductsJSONPath = "discountedProducts.json";
    private final String baseRate = "INR";

    private InputStream getURLData(String url) {
        InputStream result = null;
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            result = conn.getInputStream();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ProductFactory:getURLData:: " + e.toString());
        }
        return result;
    }

    private void fetchProductData(InputStream respStream) {
        try {
            ObjectMapper mapper = new ObjectMapper().configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            this.products = mapper.readValue(respStream,
                    ProductResponse[].class);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ProductFactory:fetchProductData:: " + e.toString());
        }
    }

    private void fetchExchangeRates(InputStream respStream) {
        try {
            ObjectMapper mapper = new ObjectMapper().configure(
                    DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                    false);
            ExchangeRateResponse exr = mapper.readValue(respStream,
                    ExchangeRateResponse.class);
            this.exchangeRate = exr.getRates();

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ProductFactory:fetchExchangeRates:: " + e.toString());
        }
    }

    private void applyBaseRate() {
        try {
            for (ProductResponse prod : this.products) {
                if (!prod.getCurrency().equals(this.baseRate)) {
                    prod.setPrice(this.exchangeRate.get(
                            prod.getCurrency()) * prod.getPrice());
                    prod.setCurrency(this.baseRate);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ProductFactory:applyBaseRate:: " + e.toString());
        }
    }

    public void saveDiscountedProductData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(Paths.get(this.discountedProductsJSONPath).toFile(), this.products);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "ProductFactory:saveDiscountedProductData:: " + e.toString());
        }
    }

    public void prePromotion() {
        try {
            // fetching data from the mock urls
            InputStream prodResp = this.getURLData(this.productURL);
            InputStream exchangeRateResp = this.getURLData(this.exchangeRateURL);

            // unmarshalling fetched json data into class members
            this.fetchProductData(prodResp);
            this.fetchExchangeRates(exchangeRateResp);
            this.applyBaseRate();
        } catch (Exception e) {
            LOGGER.severe("ProductFactory:prePromotion::" + e.toString());
        }
    }

    abstract void generateDiscountedProductJSON();
    abstract Discount calculateDiscount(ProductResponse prod);
}
