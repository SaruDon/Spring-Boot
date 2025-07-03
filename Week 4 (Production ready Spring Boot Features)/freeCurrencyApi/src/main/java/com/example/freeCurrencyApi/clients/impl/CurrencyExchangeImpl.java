package com.example.freeCurrencyApi.clients.impl;

import com.example.freeCurrencyApi.clients.CurrencyExchange;
import com.example.freeCurrencyApi.dto.CurrencyData;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeImpl implements CurrencyExchange {


    private  final RestClient restClient;


    @Override
    public Double ConvertToCurrency(String fromCurrency, String toCurrency, Double units) {
        CurrencyData currencyData = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("apikey", "fca_live_vcsFBl9O6nar0jPtrm9ZxBRjPFVsSGzc4c30CZBG")
                        .queryParam("currencies", toCurrency)  // Get the target currency rate
                        .queryParam("base_currency", fromCurrency)  // Base currency is the source
                        .build())
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new HttpClientErrorException(res.getStatusCode(), res.getBody().toString());
                })
                .body(new ParameterizedTypeReference<CurrencyData>() {});

        return currencyData.getData().get(toCurrency) * units;
    }

}
