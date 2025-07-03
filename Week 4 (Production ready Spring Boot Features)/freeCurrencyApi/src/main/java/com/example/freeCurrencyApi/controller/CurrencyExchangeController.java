package com.example.freeCurrencyApi.controller;

import com.example.freeCurrencyApi.clients.CurrencyExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CurrencyExchangeController {

    private final CurrencyExchange currencyExchange;

    @GetMapping("convertCurrency")
    private ResponseEntity<Double> getCurrency(@RequestParam String fromCurrency, @RequestParam String toCurrency, @RequestParam Double units){
        return ResponseEntity.ok(currencyExchange.ConvertToCurrency(fromCurrency,toCurrency,units));
    }

}
