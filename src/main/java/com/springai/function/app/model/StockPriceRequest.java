package com.springai.function.app.model;

import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonInclude(JsonInclude.Include.NON_NULL) 
@JsonClassDescription("Stock API request using Ticker") 
public record StockPriceRequest(@JsonProperty(required = true, value="ticker") 
								@JsonPropertyDescription("Ticker for a Stock")String ticker,
								@JsonProperty(required = false, value="name") 
								@JsonPropertyDescription("Name of the for a Stock")String name) {

}
