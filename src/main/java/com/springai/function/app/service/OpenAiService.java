package com.springai.function.app.service;

import com.springai.function.app.model.Answer;
import com.springai.function.app.model.Question;

public interface OpenAiService {
	
	Answer getWeatherDetails(Question question);

}
