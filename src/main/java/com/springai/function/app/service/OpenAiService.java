package com.springai.function.app.service;

import com.springai.function.app.model.Answer;
import com.springai.function.app.model.Question;
import com.springai.function.app.model.StockPriceRequest;
import com.springai.function.app.model.StockPriceResponse;

public interface OpenAiService {
	
	Answer getWeatherDetails(Question question);
	
	Answer getStockPrice(Question priceRequest);

}
