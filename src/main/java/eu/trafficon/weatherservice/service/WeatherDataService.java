package eu.trafficon.weatherservice.service;

import eu.trafficon.weatherservice.exception.DataNotFoundException;  // Import custom exceptions
import eu.trafficon.weatherservice.exception.DataRetrievalException;
import eu.trafficon.weatherservice.model.WeatherResponse;
import eu.trafficon.weatherservice.repository.WeatherResponseRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class WeatherDataService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherDataService.class);

    private final WeatherResponseRepository weatherResponseRepository;

    public WeatherDataService(WeatherResponseRepository weatherResponseRepository) {
        this.weatherResponseRepository = weatherResponseRepository;
    }

    public List<WeatherResponse> getAllWeatherData() {
        try {
            return weatherResponseRepository.findAll();
        } catch (Exception e) {
            logger.error("Error occurred while retrieving weather data", e);
            throw new DataRetrievalException("Could not fetch weather data", e);
        }
    }

    public Optional<WeatherResponse> getWeatherDataById(Long id) {
        try {
            return weatherResponseRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error occurred while retrieving weather data for ID: " + id, e);
            throw new DataRetrievalException("Could not fetch weather data for ID: " + id, e);
        }
    }
}
