package eu.trafficon.weatherservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import eu.trafficon.weatherservice.model.WeatherResponse;
import eu.trafficon.weatherservice.exception.DataRetrievalException;  // Import custom exception
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class APIService {

    private static final Logger logger = LoggerFactory.getLogger(APIService.class);

    @Value("${weather.api.url}")
    private String apiUrl;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.host}")
    private String apiHost;

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    public APIService(ObjectMapper objectMapper, HttpClient httpClient) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
    }

    public WeatherResponse fetchWeatherData() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("x-rapidapi-key", apiKey)
                    .header("x-rapidapi-host", apiHost)
                    .GET()
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Log the entire API response for debugging purposes
            logger.info("Received API response: {}", response.body());

            if (response.statusCode() == HttpStatus.OK.value()) {
                return objectMapper.readValue(response.body(), WeatherResponse.class);
            } else {
                logger.error("Failed to fetch weather data. Status code: {}", response.statusCode());
                throw new DataRetrievalException("Failed to fetch weather data. Status code: " + response.statusCode());
            }

        } catch (Exception e) {
            logger.error("Exception occurred while fetching weather data: ", e);
            throw new DataRetrievalException("Exception occurred while fetching weather data.", e);
        }
    }
}
