package com.itis.template.di

import com.itis.template.data.weather.WeatherRepositoryImpl
import com.itis.template.data.weather.datasource.remote.WeatherApi
import com.itis.template.domain.weather.WeatherRepository
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi
    ): WeatherRepository = WeatherRepositoryImpl(weatherApi)
}