package eu.trafficon.weatherservice.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "weather_response")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Embedded
    @JsonProperty("coord")
    private Coordinates coord;

    @OneToMany(mappedBy = "weatherResponse", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty("weather")
    private List<WeatherCondition> weather;

    @Column(name = "base")
    @JsonProperty("base")
    private String base;

    @Embedded
    @JsonProperty("main")
    private MainWeatherInfo main;

    @Column(name = "visibility")
    @JsonProperty("visibility")
    private int visibility;

    @Embedded
    @JsonProperty("wind")
    private WindInfo wind;

    @Embedded
    @JsonProperty("clouds")
    private CloudCoverage clouds;

    @Column(name = "dt")
    @JsonProperty("dt")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private long dt;

    @Embedded
    @JsonProperty("sys")
    private SystemInfo sys;

    @Column(name = "timezone")
    @JsonProperty("timezone")
    private int timezone;

    @Column(name = "response_id")
    @JsonProperty("id")
    private long responseId;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "cod")
    @JsonProperty("cod")
    private int cod;
}
