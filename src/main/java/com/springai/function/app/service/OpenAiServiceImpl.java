package com.springai.function.app.service;

import java.util.List;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springai.function.app.function.WeatherServiceFunction;
import com.springai.function.app.model.Answer;
import com.springai.function.app.model.Question;
import com.springai.function.app.model.WeatherRequest;

@Service
public class OpenAiServiceImpl implements OpenAiService{

	private OpenAiChatModel aiChatModel;
	

	@Value("${weather-app.end-point}")
	private String weatherAppEndPoint;

	@Value("${weather-app.api-key}")
    private String apiNinjasKey;   
	
	
	public OpenAiServiceImpl(OpenAiChatModel aiChatModel) {
		this.aiChatModel = aiChatModel;
	}
	
	
	@Override
	public Answer getWeatherDetails(Question question) {
		
	       var promptOptions = OpenAiChatOptions.builder()
	                .functionCallbacks(List.of(FunctionCallback.builder()
	                                .function("CurrentWeather", new WeatherServiceFunction(weatherAppEndPoint,apiNinjasKey))
	                                .description("Get the current weather for a location")
									.inputType(WeatherRequest.class)
	                      .build()))
	                .build();
	       
         Message userMessage = new PromptTemplate(question.question()).createMessage();
         
         var response = aiChatModel.call(new Prompt(List.of(userMessage), promptOptions));
         
         
		return new Answer(response.getResult().getOutput().getContent());
	}

}