package com.springai.function.app.function;

import java.util.function.Function;

import org.springframework.web.client.RestClient;

import com.springai.function.app.model.StockPriceRequest;
import com.springai.function.app.model.StockPriceResponse;

public class StockServiceFunction implements Function<StockPriceRequest, StockPriceResponse> {

	private String stockApiEndPoint;
	
    private String apiNinjasKey;
    
    public StockServiceFunction(String stockApiEndPoint, String apiNinjasKey) {
    	this.stockApiEndPoint = stockApiEndPoint;
    	this.apiNinjasKey = apiNinjasKey;
    }
	
	
	@Override
	public StockPriceResponse apply(StockPriceRequest stockPriceRequest) {
		
		RestClient restclient = RestClient.builder()
								.baseUrl(stockApiEndPoint)
				                .defaultHeaders(httpHeaders -> {
				                    httpHeaders.set("X-Api-Key", apiNinjasKey);
				                    httpHeaders.set("Accept", "application/json");
				                    httpHeaders.set("Content-Type", "application/json");
				                }).build();
		
			return restclient.get().uri(uriBuilder -> {
				
				uriBuilder.queryParam("ticker", stockPriceRequest.ticker());
				
				return uriBuilder.build();
				
			}).retrieve().body(StockPriceResponse.class);
	}

}
