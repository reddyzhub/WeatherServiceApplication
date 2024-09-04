package eu.trafficon.weatherservice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemInfo {

    @Column(name = "sys_type")
    @JsonProperty("type")
    private int type;

    @Column(name = "sys_id")
    @JsonProperty("id")
    private long systemId;

    @Column(name = "sys_country")
    @JsonProperty("country")
    private String sysCountry;

    @Column(name = "sunrise")
    @JsonProperty("sunrise")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private long sunrise;

    @Column(name = "sunset")
    @JsonProperty("sunset")
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private long sunset;
}
