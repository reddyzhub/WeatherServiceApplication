package eu.trafficon.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Coordinates {

    @Column(name = "lon")
    @JsonProperty("lon")
    private double lon;

    @Column(name = "lat")
    @JsonProperty("lat")
    private double lat;
}
