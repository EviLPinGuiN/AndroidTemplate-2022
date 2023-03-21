package com.itis.template.presentation.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.itis.template.di.DataContainer
import com.itis.template.domain.weather.GetWeatherUseCase
import com.itis.template.domain.weather.WeatherInfo
import kotlinx.coroutines.launch

class MainViewModel(
    private val getWeatherUseCase: GetWeatherUseCase
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?>
        get() = _error

    private val _weatherInfo = MutableLiveData<WeatherInfo?>(null)
    val weatherInfo: LiveData<WeatherInfo?>
        get() = _weatherInfo

    val navigateDetails = MutableLiveData<Boolean?>(null)

    fun onLoadClick(query: String) {
        loadWeather(query)
    }

    private fun loadWeather(query: String) {
        viewModelScope.launch {
            try {
                _loading.value = true
                getWeatherUseCase(query).also { weatherInfo ->
                    _weatherInfo.value = weatherInfo
                }
            } catch (error: Throwable) {
                _error.value = error
            } finally {
                _loading.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val useCase = DataContainer.weatherUseCase
                // Create a SavedStateHandle for this ViewModel from extras
//                val savedStateHandle = extras.createSavedStateHandle()
                return MainViewModel(useCase) as T
            }
        }

        fun provideFactory(
            useCase: GetWeatherUseCase
        ): ViewModelProvider.Factory = viewModelFactory {
            initializer {
                // Create a SavedStateHandle for this ViewModel from extras
//                val savedStateHandle = extras.createSavedStateHandle()
                MainViewModel(useCase)
            }
        }

    }
}