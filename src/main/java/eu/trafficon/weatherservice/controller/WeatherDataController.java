package eu.trafficon.weatherservice.controller;

import eu.trafficon.weatherservice.exception.DataNotFoundException;
import eu.trafficon.weatherservice.model.WeatherResponse;
import eu.trafficon.weatherservice.service.WeatherDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/weather-data")
public class WeatherDataController {

    private final WeatherDataService weatherDataService;

    public WeatherDataController(WeatherDataService weatherDataService) {
        this.weatherDataService = weatherDataService;
    }

    /**
     * Get all weather data records.
     *
     * @return ResponseEntity with the list of WeatherResponse objects.
     */
    @GetMapping("/all")
    public ResponseEntity<List<WeatherResponse>> getAllWeatherData() {
        List<WeatherResponse> weatherDataList = weatherDataService.getAllWeatherData();
        return ResponseEntity.ok(weatherDataList);
    }

    /**
     * Get weather data by ID.
     *
     * @param id - The ID of the weather response.
     * @return ResponseEntity with the WeatherResponse object or a 404 status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<WeatherResponse> getWeatherDataById(@PathVariable Long id) {
        Optional<WeatherResponse> weatherResponse = weatherDataService.getWeatherDataById(id);
        return weatherResponse.map(ResponseEntity::ok)
                .orElseThrow(() -> new DataNotFoundException("Weather data not found for ID: " + id));
    }
}
