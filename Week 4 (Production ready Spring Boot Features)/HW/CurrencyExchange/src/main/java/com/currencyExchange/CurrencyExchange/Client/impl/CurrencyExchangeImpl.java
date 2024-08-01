package com.currencyExchange.CurrencyExchange.Client.impl;



import com.currencyExchange.CurrencyExchange.Client.CurrencyExchangeClient;
import com.currencyExchange.CurrencyExchange.config.RestClientConfig;
import com.currencyExchange.CurrencyExchange.entity.Quota;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyExchangeImpl implements CurrencyExchangeClient {

    private  final RestClientConfig restClientConfig;


    @Override
    public Quota getQuota() {
        try{
            Quota quota = restClientConfig.get()
                    .uri()
        }catch (Exception e){

        }
    }
}
