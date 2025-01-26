# Leveraging Spring AI Framework with OpenAI APIs and API-Ninja Weather API

This project demonstrates how to use the **Spring AI Framework** in combination with **OpenAI APIs** to build a robust weather information application. By incorporating a callback function approach, the application ensures more accurate and contextually rich responses from the OpenAI API. Additionally, for development purposes, the application integrates the **API-Ninja Weather API** to fetch detailed weather data, which is then used to enhance OpenAI's responses.

---

## Key Features

1. **Spring AI Framework Integration**: Leverage the power of Spring AI for seamless integration with OpenAI APIs.
2. **Callback Function Approach**: Implement a callback function mechanism to fine-tune OpenAI responses for greater accuracy and relevance.
3. **API-Ninja Weather API**: Fetch real-time weather data from API-Ninja to enrich the application's functionality.
4. **Weather Insights via OpenAI**: Combine API-Ninja's raw weather data with OpenAI's capabilities to generate detailed, user-friendly weather insights for a specified city.

---

## How It Works

1. **User Input**: The user provides the name of a city.
2. **API-Ninja Integration**: The application fetches the city's weather details using the API-Ninja Weather API.
3. **Callback Function**: A callback mechanism processes the weather data and sends a refined query to the OpenAI API.
4. **OpenAI Response**: OpenAI generates a detailed, context-aware response about the city's weather.
5. **Output**: The application returns a comprehensive weather report to the user.
