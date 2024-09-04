package eu.trafficon.weatherservice.controller;

import eu.trafficon.weatherservice.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//  implemented to manually trigger. Fetches and saves weather data from external api
//  http://localhost:8080/api/weather/fetch

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @Operation(summary = "Fetch and save weather data from external API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Weather data fetched and saved successfully"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/fetch")
    public ResponseEntity<String> fetchWeatherData() {
        try {
            weatherService.saveWeatherData();
            return ResponseEntity.ok("Weather data fetched and saved successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred while fetching weather data.");
        }
    }
}
