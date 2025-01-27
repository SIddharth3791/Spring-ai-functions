package com.springai.function.app.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record StockPriceResponse(@JsonPropertyDescription("Ticker of the Stock") String ticker, 
								 @JsonPropertyDescription("Name of the Stock") String name, 
								 @JsonPropertyDescription("Price  of the Stock") float price, 
								 @JsonPropertyDescription("Currency  of the Stock") String currency,
								 @JsonPropertyDescription("Name of the Exchange the stock belongs to")String exchange) {

}
