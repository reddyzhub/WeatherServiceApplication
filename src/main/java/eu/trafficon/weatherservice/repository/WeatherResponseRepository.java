package eu.trafficon.weatherservice.repository;

import eu.trafficon.weatherservice.model.WeatherResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherResponseRepository extends JpaRepository<WeatherResponse, Long> {
}
