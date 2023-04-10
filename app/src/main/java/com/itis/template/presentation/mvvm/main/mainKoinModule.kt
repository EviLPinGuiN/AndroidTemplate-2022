package com.itis.template.presentation.mvvm.main

import com.itis.template.data.weather.WeatherRepositoryImpl
import com.itis.template.domain.weather.GetWeatherUseCase
import com.itis.template.domain.weather.WeatherRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainKoinModule = module {
    factory<WeatherRepository> { WeatherRepositoryImpl(get()) }
    factory { GetWeatherUseCase(get()) }
    viewModel { MainViewModel(get()) }
}
