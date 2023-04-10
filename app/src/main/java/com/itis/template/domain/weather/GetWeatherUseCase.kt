package com.itis.template.domain.weather

import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    operator fun invoke(
        query: String
    ): Single<WeatherInfo> = weatherRepository.getWeather(query)

}