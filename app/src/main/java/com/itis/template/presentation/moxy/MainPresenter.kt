package com.itis.template.presentation.moxy

import com.itis.template.domain.weather.GetWeatherUseCase
import kotlinx.coroutines.launch
import moxy.MvpPresenter
import moxy.presenterScope

class MainMoxyPresenter(
    private val getWeatherUseCase: GetWeatherUseCase,
): MvpPresenter<MainMoxyView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

    }

    override fun onDestroy() {
        super.onDestroy()

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
                viewState.showLoading(true)
                getWeatherUseCase(query).subscribe { weatherInfo ->
                    viewState.showTemp(weatherInfo.temperature)
                    viewState.showWeatherIcon(weatherInfo.icon)
                }
            } catch (error: Throwable) {
                viewState.showError(error)
            } finally {
                viewState.showLoading(false)
            }
        }
    }
}
