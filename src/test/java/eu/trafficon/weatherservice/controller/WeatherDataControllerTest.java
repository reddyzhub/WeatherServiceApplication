package eu.trafficon.weatherservice.controller;

import eu.trafficon.weatherservice.model.WeatherResponse;
import eu.trafficon.weatherservice.service.WeatherDataService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WeatherDataController.class)
public class WeatherDataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WeatherDataService weatherDataService;

    @Test
    void testGetWeatherDataById() throws Exception {
        // Arrange: Create and initialize the WeatherResponse object
        WeatherResponse response = new WeatherResponse();
        // Set fields that are included in serialization
        response.setCoord(null);  // Assuming the real value
        response.setWeather(null);  // Assuming the real value
        response.setBase(null);  // Assuming the real value
        response.setMain(null);  // Assuming the real value
        response.setVisibility(1000);
        response.setWind(null);  // Assuming the real value
        response.setClouds(null);  // Assuming the real value
        response.setDt(123456789);
        response.setSys(null);  // Assuming the real value
        response.setTimezone(3600);
        response.setName("Sample City");
        response.setCod(200);

        // Mock the service layer to return this object
        Mockito.when(weatherDataService.getWeatherDataById(2L)).thenReturn(Optional.of(response));

        // Act: Perform the GET request
        mockMvc.perform(get("/api/weather-data/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())  // Print response for debugging
                .andExpect(status().isOk())
                // Validate the fields that are serialized
                .andExpect(jsonPath("$.coord").isEmpty())
                .andExpect(jsonPath("$.weather").isEmpty())
                .andExpect(jsonPath("$.base").isEmpty())
                .andExpect(jsonPath("$.main").isEmpty())
                .andExpect(jsonPath("$.visibility").value(response.getVisibility()))
                .andExpect(jsonPath("$.wind").isEmpty())
                .andExpect(jsonPath("$.clouds").isEmpty())
                .andExpect(jsonPath("$.dt").value(response.getDt()))
                .andExpect(jsonPath("$.sys").isEmpty())
                .andExpect(jsonPath("$.timezone").value(response.getTimezone()))
                .andExpect(jsonPath("$.name").value(response.getName()))
                .andExpect(jsonPath("$.cod").value(response.getCod()));
    }
}
