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
public class WindInfo {

    @Column(name = "wind_speed")
    @JsonProperty("speed")
    private double speed;

    @Column(name = "wind_deg")
    @JsonProperty("deg")
    private int deg;

    @Column(name = "wind_gust")
    @JsonProperty("gust")
    private Double gust;
}
