package com.itis.template.domain.weather

import io.reactivex.rxjava3.core.Single

interface WeatherRepository {

    fun getWeather(query: String): Single<WeatherInfo>
}