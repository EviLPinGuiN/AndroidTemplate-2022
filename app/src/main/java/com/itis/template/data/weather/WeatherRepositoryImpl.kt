package com.itis.template.data.weather

import com.itis.template.data.weather.datasource.remote.WeatherApi
import com.itis.template.data.weather.mapper.toWeatherInfo
import com.itis.template.domain.weather.WeatherInfo
import com.itis.template.domain.weather.WeatherRepository
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.internal.operators.single.SingleFromCallable
import io.reactivex.rxjava3.schedulers.Schedulers

class WeatherRepositoryImpl(
    private val api: WeatherApi
) : WeatherRepository {

    override fun getWeather(
        query: String
    ): Single<WeatherInfo> = api.getWeather(query)
        .map {
            it.toWeatherInfo()
        }
        .subscribeOn(Schedulers.io())
}