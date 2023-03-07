package com.itis.template.presentation.mvp

import com.itis.template.domain.weather.GetWeatherUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainPresenter(
    private val view: MainView,
    private val getWeatherUseCase: GetWeatherUseCase,
) {

    private val presenterScope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    fun onClear() {
        presenterScope.cancel()
    }

    fun onSearchClick(query: String) {
        loadWeather(query)
    }

    fun onLoadClick(query: String) {
        loadWeather(query)
    }

    private fun loadWeather(query: String) {
        presenterScope.launch {
            try {
                view.showLoading(true)
                getWeatherUseCase(query).also { weatherInfo ->
                    view.showTemp(weatherInfo.temperature)
                    view.showWeatherIcon(weatherInfo.icon)
                }
            } catch (error: Throwable) {
                view.showError(error)
            } finally {
                view.showLoading(false)
            }
        }
    }
}
