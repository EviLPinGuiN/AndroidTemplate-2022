package com.itis.template.data.weather

import com.itis.template.data.weather.datasource.remote.WeatherApi
import com.itis.template.data.weather.mapper.toWeatherInfo
import com.itis.template.domain.weather.WeatherInfo
import com.itis.template.domain.weather.WeatherRepository

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeather(
        query: String
    ): WeatherInfo = api.getWeather(query).toWeatherInfo()
}