package eu.trafficon.weatherservice.service;

import eu.trafficon.weatherservice.exception.DataRetrievalException; // Import custom exception
import eu.trafficon.weatherservice.model.WeatherCondition;
import eu.trafficon.weatherservice.model.WeatherResponse;
import eu.trafficon.weatherservice.repository.WeatherResponseRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    private final WeatherResponseRepository weatherResponseRepository;
    private final APIService apiService;

    public WeatherService(WeatherResponseRepository weatherResponseRepository, APIService apiService) {
        this.weatherResponseRepository = weatherResponseRepository;
        this.apiService = apiService;
    }

    @Transactional
    public void saveWeatherData() {
        try {
            WeatherResponse weatherResponse = apiService.fetchWeatherData();
            if (weatherResponse != null) {
                // Ensures weather conditions are properly associated
                if (weatherResponse.getWeather() != null) {
                    for (WeatherCondition condition : weatherResponse.getWeather()) {
                        condition.setWeatherResponse(weatherResponse); // Associate each WeatherCondition with the WeatherResponse
                    }
                    weatherResponseRepository.save(weatherResponse);
                    logger.info("Weather data saved successfully.");
                } else {
                    logger.warn("No weather data fetched; nothing to save.");
                }
            }
        } catch (DataRetrievalException e) {
            logger.error("Error occurred while saving weather data", e);
        }
    }

    //scheduler
    @Scheduled(fixedRateString = "${weather.data.fetch.rate}")
    public void scheduledFetchAndSaveWeatherData() {
        logger.info("Scheduled task started: Fetching and saving weather data.");
        saveWeatherData();
    }
}
