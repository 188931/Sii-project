package com.example.charity_app.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyExchangeService {
    private final Map<String, BigDecimal> exchangeRates = new HashMap<>();
    
    public CurrencyExchangeService() {
        exchangeRates.put("EUR", BigDecimal.ONE);
        exchangeRates.put("USD", new BigDecimal("0.85"));
        exchangeRates.put("GBP", new BigDecimal("1.17"));
        exchangeRates.put("PLN", new BigDecimal("0.22"));
    }
    
    public BigDecimal convert(BigDecimal amount, String fromCurrency, String toCurrency) {
        if (fromCurrency.equals(toCurrency)) {
            return amount;
        }
        
        BigDecimal fromRate = exchangeRates.get(fromCurrency);
        BigDecimal toRate = exchangeRates.get(toCurrency);
        
        if (fromRate == null || toRate == null) {
            throw new IllegalArgumentException("Unsupported currency");
        }
        
        BigDecimal amountInEur = amount.multiply(fromRate);
        return amountInEur.divide(toRate, 2, RoundingMode.HALF_UP);
    }
}
