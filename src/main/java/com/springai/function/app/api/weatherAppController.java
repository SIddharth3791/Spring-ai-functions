package com.springai.function.app.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springai.function.app.model.Answer;
import com.springai.function.app.model.Question;
import com.springai.function.app.service.OpenAiService;


@RestController
public class weatherAppController {
	
	private OpenAiService openAiService;
	
	public weatherAppController(OpenAiService openAiService) {
		this.openAiService = openAiService;
	}

	
	@PostMapping("/weather")
	public Answer getWeatherDetails(@RequestBody Question question) {
		return openAiService.getWeatherDetails(question);
	}
	
}
