package com.example.reactivekotlindemo.weather

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class WeatherReading(
    val coord: Coordinate,
    val weather: List<QualitativeReading>,
    val base: String,
    val main: MainReading,
    val visibility: Long,
    val wind: WindSpeed,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timeZone: Long,
    val id: Long,
    val name: String,
    val cod: Long
)

data class Sys(
    val type: Long,
    val id: Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

data class WindSpeed(val speed: BigDecimal, val deg: Long)

data class Clouds(val all: Long)

data class Coordinate(val lon: BigDecimal, val lat: BigDecimal)

data class QualitativeReading(val id: Long, val main: String, val description: String, val icon: String)

data class MainReading(
    val temp: BigDecimal,
    val pressure: Long,
    val humidity: Long,
    @JsonProperty("temp_min") val tempMin: BigDecimal,
    @JsonProperty("temp_max") val tempMax: BigDecimal,
    @JsonProperty("feels_like") val feelsLike: BigDecimal
)