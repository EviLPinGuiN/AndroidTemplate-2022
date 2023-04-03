package com.itis.template.di.hilt

import com.itis.template.data.weather.WeatherRepositoryImpl
import com.itis.template.data.weather.datasource.remote.WeatherApi
import com.itis.template.domain.weather.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class WeatherModuleHilt {

    @Provides
    fun provideWeatherRepository(
        weatherApi: WeatherApi
    ): WeatherRepository = WeatherRepositoryImpl(weatherApi)
}