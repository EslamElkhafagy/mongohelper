package com.example.mongodb.annotation;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathHelper {

    private MathHelper() {
    }

    public static BigDecimal roundDecimalNumber(Double value, int newScale) {

        if (value != null)
            return BigDecimal.valueOf(value).setScale(newScale, RoundingMode.DOWN);
        else
            return null;
    }

    public static Double roundUp(Double value, int newScale) {
        if (value != null)
            return new BigDecimal(Double.toString(value)).setScale(newScale, RoundingMode.HALF_UP).doubleValue();

        return null;
    }
}
