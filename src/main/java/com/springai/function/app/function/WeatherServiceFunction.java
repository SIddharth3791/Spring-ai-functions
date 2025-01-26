package com.springai.function.app.function;


import java.util.function.Function;

import org.springframework.web.client.RestClient;

import com.springai.function.app.model.WeatherRequest;
import com.springai.function.app.model.WeatherResponse;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WeatherServiceFunction implements Function<WeatherRequest, WeatherResponse> {


	private String weatherAppEndPoint;

    private String apiNinjasKey;   
	
	public WeatherServiceFunction(String weatherAppEndPoint,String apiNinjasKey) {
		this.weatherAppEndPoint = weatherAppEndPoint;
		this.apiNinjasKey = apiNinjasKey;
	}
	

    @Override
    public WeatherResponse apply(WeatherRequest weatherRequest) {
        RestClient restClient = RestClient.builder()
                .baseUrl(weatherAppEndPoint)
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.set("X-Api-Key", apiNinjasKey);
                    httpHeaders.set("Accept", "application/json");
                    httpHeaders.set("Content-Type", "application/json");
                }).build();

        return restClient.get().uri(uriBuilder -> {
            System.out.println("Building URI for weather request: " + weatherRequest);

            uriBuilder.queryParam("lat", weatherRequest.latitude())
            .queryParam("lon", weatherRequest.longitude());
            
            return uriBuilder.build();
        }).retrieve().body(WeatherResponse.class);
    }

}
