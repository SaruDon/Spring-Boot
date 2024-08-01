package com.currencyExchange.CurrencyExchange.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Quota {
    @JsonProperty("quotas")
    private Quotas quotas;

    // Getters and Setters

    public static class Quotas {
        @JsonProperty("month")
        private Month month;

        // Getters and Setters
    }

    public static class Month {
        @JsonProperty("total")
        private int total;

        @JsonProperty("used")
        private int used;

        @JsonProperty("remaining")
        private int remaining;

        // Getters and Setters
    }
}
