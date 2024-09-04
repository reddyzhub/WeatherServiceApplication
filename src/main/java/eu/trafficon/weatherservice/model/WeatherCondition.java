package eu.trafficon.weatherservice.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "weather_condition")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "weather_id")
    @JsonProperty("id")
    private int weatherId;

    @Column(name = "main")
    private String main;

    @Column(name = "description")
    @JsonProperty("description")
    private String description;

    @Column(name = "icon")
    @JsonProperty("icon")
    private String icon;

    @ManyToOne
    @JoinColumn(name = "weather_response_id")  // Foreign key column in weather_condition table
    @JsonIgnore
    private WeatherResponse weatherResponse;
}
