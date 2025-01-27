package com.springai.function.app.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springai.function.app.model.Answer;
import com.springai.function.app.model.Question;
import com.springai.function.app.service.OpenAiService;

@RestController
public class StockPriceController {
	
	private OpenAiService openAiService;
	
	public StockPriceController(OpenAiService openAiService) {
		this.openAiService = openAiService;
	}
	
	@PostMapping("/price")
	public Answer getStockPrice(@RequestBody Question question) {
		return openAiService.getStockPrice(question);
	}

}
