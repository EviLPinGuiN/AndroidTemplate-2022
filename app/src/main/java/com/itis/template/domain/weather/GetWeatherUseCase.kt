package com.itis.template.domain.weather

import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(
        query: String
    ): WeatherInfo = weatherRepository.getWeather(query)

}