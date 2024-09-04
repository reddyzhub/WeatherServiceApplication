package eu.trafficon.weatherservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonProperty;

@Embeddable
@Data
public class CloudCoverage {

    @Column(name = "cloud_all")
    @JsonProperty("all")
    private int clouds;
}
