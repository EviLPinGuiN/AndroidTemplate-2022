package com.itis.template.domain.weather

interface WeatherRepository {

    suspend fun getWeather(query: String): WeatherInfo
}