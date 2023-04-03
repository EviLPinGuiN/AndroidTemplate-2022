package com.itis.template.domain.weather

import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Inject

@InstallIn(ActivityComponent::class)
class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend operator fun invoke(
        query: String
    ): WeatherInfo = weatherRepository.getWeather(query)

}