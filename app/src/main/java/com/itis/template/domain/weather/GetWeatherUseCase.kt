package com.itis.template.domain.weather

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(
        query: String
    ): WeatherInfo = weatherRepository.getWeather(query)

}