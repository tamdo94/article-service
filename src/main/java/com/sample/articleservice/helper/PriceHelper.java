package com.sample.articleservice.helper;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PriceHelper {

    public static BigDecimal priceAfterDiscount(BigDecimal prePrice, Double rate) {
        Assert.notNull(prePrice, "price is required");
        Assert.notNull(rate, "rate is required");

        BigDecimal discount = prePrice.multiply(BigDecimal.valueOf(rate)).divide(BigDecimal.valueOf(100));
        return prePrice.subtract(discount).setScale(2, RoundingMode.UP);
    }
}
