package com.springai.function.app.service;

import java.util.List;

import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.model.ModelOptionsUtils;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.springai.function.app.function.StockServiceFunction;
import com.springai.function.app.function.WeatherServiceFunction;
import com.springai.function.app.model.Answer;
import com.springai.function.app.model.Question;
import com.springai.function.app.model.StockPriceRequest;
import com.springai.function.app.model.StockPriceResponse;
import com.springai.function.app.model.WeatherRequest;
import com.springai.function.app.model.WeatherResponse;

@Service
public class OpenAiServiceImpl implements OpenAiService{

	private OpenAiChatModel aiChatModel;
	

	@Value("${weather-app.end-point}")
	private String weatherAppEndPoint;
	
	@Value("${weather-app.api-key}")
    private String apiNinjasKey;   
	
	@Value("${weather-app.user-message}")
	private String weatherPromptUserMessage;
	
	
	@Value("${stock-app.end-point}")
	private String stockApiEndPoint;
	
	@Value("${stock-app.user-message}")
	private String stockPricePromptUserMessage;
	
	
	
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
									.responseConverter(response ->{
										String schema = ModelOptionsUtils.getJsonSchema(WeatherResponse.class, false);
										String json = ModelOptionsUtils.toJsonString(response);
										return schema + "\n" + json;
									})
	                      .build()))
	                .build();
	       
         Message userMessage = new PromptTemplate(question.question()).createMessage();
         
         Message systemMessage = new SystemPromptTemplate(weatherPromptUserMessage).createMessage();
         
         var response = aiChatModel.call(new Prompt(List.of(userMessage, systemMessage), promptOptions));
         
		return new Answer(response.getResult().getOutput().getContent());
	}


	@Override
	public Answer getStockPrice(Question question) {
		
		var promptOptions = OpenAiChatOptions.builder()
							.functionCallbacks(List.of(FunctionCallback.builder()
								.function("StockPrice", new StockServiceFunction(stockApiEndPoint, apiNinjasKey))
								.description("Get Current price details of Stock")
								.inputType(StockPriceRequest.class)
								.responseConverter(response ->{
									String schema = ModelOptionsUtils.getJsonSchema(StockPriceResponse.class, false);
									String json = ModelOptionsUtils.toJsonString(response);
									return json;
								})
							.build()))
						.build();
		
		Message usermsg = new PromptTemplate(question.question()).createMessage();
		
		Message sysMsg = new SystemPromptTemplate(stockPricePromptUserMessage).createMessage();
		
		var response  = aiChatModel.call(new Prompt(List.of(usermsg, sysMsg), promptOptions));
		
		BeanOutputConverter<StockPriceResponse> converter = new BeanOutputConverter<>(StockPriceResponse.class);
		
		StockPriceResponse stockPriceResponse = converter.convert(response.getResult().getOutput().getContent());

	
		return new Answer(response.getResult().getOutput().getContent());
	}
	
	
	

}