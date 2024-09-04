package eu.trafficon.weatherservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.http.HttpClient;

/**
 * Configuration class for the Weather Service application.
 * This class is used to define beans and other configuration settings
 * for the Spring application context.
 */
@Configuration
public class AppConfig {

    /**
     * Bean definition for HttpClient.
     *
     * This method creates and configures an instance of HttpClient,
     * which is a modern, non-blocking HTTP client built into Java.
     * By defining it as a bean, it becomes available for dependency injection
     * throughout the Spring application, promoting code reuse and making
     * the application more testable and maintainable.
     *
     * @return a new instance of HttpClient
     */
    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }
}
