package com.example.freeCurrencyApi.clients;

import java.util.List;

public interface CurrencyExchange {
    Double ConvertToCurrency(String fromCurrency,String toCurrency,Double units);
}
