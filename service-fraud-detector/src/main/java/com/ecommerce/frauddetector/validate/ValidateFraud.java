package com.ecommerce.frauddetector.validate;

import com.ecommerce.frauddetector.models.Order;

public class ValidateFraud {

    public static boolean checkIsFraud(Order order) {
        return validatePriceAmountIsFraud(order);
    }

    private static boolean validatePriceAmountIsFraud(Order order) {
        boolean isFraud = false;
        // simulate a fictitious rule when if price is great than 200, the order is a fraud
        if (order.getPrice() > 200) {
            isFraud = true;
        }
        return isFraud;
    }
}
