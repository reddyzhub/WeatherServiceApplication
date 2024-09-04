# WeatherServiceApplication
### Weather Service Application for Trafficon
This repository contains a Spring Boot application developed for a backend task. The application fetches weather data from an external API using **RapidAPI** and stores it in a local PostgreSQL database with PostGIS extensions. The weather data is updated daily, and users can retrieve stored data through various endpoints. The application is flexible and can be customized to fetch data for different locations or frequencies as required.

## Features

- **Customizable Location and Frequency**: The application is currently configured to fetch weather data for Salzburg, updating the database daily. This can be easily customized to fetch data for different locations or update frequencies.
- **Weather Data Integration**: Automatically fetches and saves weather data from external APIs using **RapidAPI**.
- **Manual Data Fetching**: Provides an endpoint to manually trigger data fetching and storage from the external API.
- **Geospatial Data Management**: Utilizes PostGIS to handle geospatial data within PostgreSQL, supporting advanced spatial queries.
- **RESTful APIs**: Exposes endpoints to retrieve weather data by specific criteria such as ID or fetch all available records.
- **Swagger Integration**: Provides an interactive API documentation and testing interface through Swagger UI.
- **Containerized Deployment**: Fully containerized using Docker, facilitating easy deployment in various environments.

## Repository and Task Overview

This application was developed as part of a backend assessment task for **Trafficon**. The task required creating a simple Spring Boot application with the following capabilities:

- Import data into a database.
- Retrieve data from the database.

### Task Requirements

- **Data Import and Retrieval**: The application allows users to import data from an external API and store it in a PostgreSQL database and provides endpoints to retrieve this data.
- **Clean Code and Best Practices**: The repository demonstrates clean code principles and best practices in Spring Boot application development.


## Technologies Used

- **Java 17**: The programming language for the backend application.
- **Spring Boot**: Framework for building the backend RESTful services.
- **PostgreSQL with PostGIS**: Relational database with spatial extensions for geospatial data management.
- **Docker**: Used to containerize the application and its dependencies.
- **Swagger**: For API documentation and testing.
- **RapidAPI**: An API marketplace used to fetch real-time weather data.

## Prerequisites

Ensure the following are installed on your machine:

- **Docker**: [Download Docker](https://www.docker.com/get-started).
- **Docker Compose**: Included with Docker installations.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/reddyzhub/WeatherServiceApplication.git
cd WeatherServiceApplication
```

### 2. Build the Docker Images

Build the Docker images using Docker Compose:

```bash
docker-compose build
```

### 3. Run the Application

Start the application using Docker Compose:

```bash
docker-compose up
```

This command initializes both the PostGIS-enabled PostgreSQL database and the Spring Boot application.

### 4. Access the Application

- **Swagger UI**: Access the API documentation and testing interface at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).
- **API Endpoints**:
    - `GET /api/weather-data/all`: Fetches all stored weather data.
    - `GET /api/weather-data/{id}`: Retrieves specific weather data by its database ID.
    - `GET /api/weather/fetch`: Manually triggers the fetching of weather data from the external API.

### 5. Stopping the Application

To stop the running application, use:

```bash
docker-compose down
```

## Docker Compose Configuration

The `docker-compose.yml` file manages both the database and the Spring Boot application:

- **PostGIS Service**:
    - **Image**: Uses `kartoza/postgis:16-3` for PostgreSQL with PostGIS.
    - **Volumes**: Persists data and initializes the database with `init.sql`.
    - **Environment Variables**: Configures the database name, user, password, and PostGIS extensions.
    - **Ports**: Exposes port `5432` for database access.
    - **Restart Policy**: Restarts on failure.

- **Spring Boot Application Service**:
    - **Build**: Uses `Dockerfile-springboot` to build the application image.
    - **Environment Variables**: Configures the database connection and Hibernate dialect for PostGIS.
    - **Ports**: Exposes port `8080` for the API.
    - **Depends On**: Ensures the database is up before starting the application.
    - **Restart Policy**: Restarts on failure.

## API Endpoints

### Weather Controller

This controller is designed for manually triggering data fetching and saving from the external API.

- **Fetch and Save Weather Data**:
    - **Endpoint**: `GET /api/weather/fetch`
    - **Description**: Manually triggers the fetching and saving of weather data from the external API.

### Weather Data Controller

This controller is used for retrieving weather data from the database.

- **Get All Weather Data**:
    - **Endpoint**: `GET /api/weather-data/all`
    - **Description**: Retrieves all stored weather data records.
    - **Response**: Returns a list of `WeatherResponse` objects.

- **Get Weather Data by ID**:
    - **Endpoint**: `GET /api/weather-data/{id}`
    - **Description**: Retrieves specific weather data by its database ID.
    - **Response**: Returns a `WeatherResponse` object or a 404 status if not found.


## Customization and Configuration

You can customize the application by modifying the `application.properties` file located in `src/main/resources`. Key configurations include:

- **API Configuration**:
  - `weather.api.url`: URL to fetch weather data. Default is Salzburg, in English.
  - `weather.api.key`: Your RapidAPI key for accessing the weather API.
  - `weather.api.host`: The host of the API, required by RapidAPI.

- **Data Fetch Frequency**:
  - `weather.data.fetch.rate`: Interval for fetching new weather data, set in milliseconds. Default is `86400000` (1 day).

### How to Customize

1. **Change Location**: Update `weather.api.url` with the desired city and language.
2. **Update API Key**: Replace `weather.api.key` with your own RapidAPI key.
3. **Adjust Fetch Rate**: Modify `weather.data.fetch.rate` to change how often data is fetched (e.g., `43200000` for 12 hours).

After making changes, rebuild and restart the application:

```bash
docker-compose down
docker-compose build
docker-compose up
```

This ensures your custom settings are applied.
### Note on External API Usage

Please be aware of the rate limits and quota restrictions of the **RapidAPI** service. Ensure you manage your API key securely and monitor usage to avoid exceeding any limits, which could impact the application's ability to fetch weather data.

Here’s a concise **Future Enhancements** section focusing on three key areas:

---

## Future Enhancements

To further improve the Weather Service Application, the following enhancements are being considered:

1. **Security Enhancements**:
  - **Implement JWT or OAuth2 Authentication**: Secure the API endpoints by implementing JSON Web Tokens (JWT) or OAuth2 authentication, ensuring that only authorized users can access or modify the weather data. This will enhance the security of the application, especially if it's exposed to the internet.

2. **Multi-Location Support**:
  - **Dynamic Location Management**: Enable support for multiple locations by allowing users to dynamically add, remove, or update locations for weather data fetching. This feature will provide users with greater flexibility and control over the weather data they are interested in.

   To add multi-location support to your Weather Service Application:

  1. **Update Database Schema**:
    - Add a `locations` table to store city, country, latitude, and longitude.
    - Modify the `weather_data` table to include a `location_id` foreign key.

  2. **Dynamic API Configuration**:
    - Remove hardcoded city URLs in `application.properties`.
    - Fetch weather data for multiple locations dynamically using the data from the `locations` table.

  3. **Enhance Backend Logic**:
    - Update service methods to loop through all locations and fetch weather data for each.
    - Add endpoints to manage locations (add, update, delete) and retrieve weather data for specific locations.

---

