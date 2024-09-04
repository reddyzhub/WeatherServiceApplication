package eu.trafficon.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainWeatherInfo {

    @Column(name = "temp")
    @JsonProperty("temp")
    private double temp;

    @Column(name = "feels_like")
    @JsonProperty("feels_like")
    private double feels_like;

    @Column(name = "temp_min")
    @JsonProperty("temp_min")
    private double temp_min;

    @Column(name = "temp_max")
    @JsonProperty("temp_max")
    private double temp_max;

    @Column(name = "pressure")
    @JsonProperty("pressure")
    private int pressure;

    @Column(name = "humidity")
    @JsonProperty("humidity")
    private int humidity;

    @Column(name = "sea_level")
    @JsonProperty("sea_level")
    private Integer sea_level;

    @Column(name = "grnd_level")
    @JsonProperty("grnd_level")
    private Integer grnd_level;
}
