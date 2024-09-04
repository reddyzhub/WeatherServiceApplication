-- Drop existing tables if they exist
DROP TABLE IF EXISTS weather_condition;
DROP TABLE IF EXISTS weather_response;

-- Create weather_response table
CREATE TABLE weather_response (
    id SERIAL PRIMARY KEY,
    lon DOUBLE PRECISION,
    lat DOUBLE PRECISION,
    base VARCHAR(50),
    temp DOUBLE PRECISION,
    feels_like DOUBLE PRECISION,
    temp_min DOUBLE PRECISION,
    temp_max DOUBLE PRECISION,
    pressure INTEGER,
    humidity INTEGER,
    sea_level INTEGER,
    grnd_level INTEGER,
    visibility INTEGER,
    wind_speed DOUBLE PRECISION,
    wind_deg INTEGER,
    wind_gust DOUBLE PRECISION,
    cloud_all INTEGER,
    dt BIGINT,
    sys_type INTEGER,
    sys_id BIGINT,
    sys_country VARCHAR(5),
    sunrise BIGINT,
    sunset BIGINT,
    timezone INTEGER,
    response_id BIGINT,
    name VARCHAR(100),
    cod INTEGER
);

-- Create weather_condition table
CREATE TABLE weather_condition (
    id SERIAL PRIMARY KEY,
    weather_response_id INTEGER REFERENCES weather_response(id) ON DELETE CASCADE,
    weather_id INTEGER,
    main VARCHAR(50),
    description VARCHAR(255),
    icon VARCHAR(10)
);
