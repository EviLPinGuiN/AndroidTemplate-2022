package com.itis.template.presentation.mvvm.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.itis.template.domain.weather.GetWeatherUseCase
import com.itis.template.domain.weather.WeatherInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.FlowableEmitter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.kotlin.Flowables
import io.reactivex.rxjava3.kotlin.Singles
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import java.util.function.Function
import javax.inject.Inject

class MainViewModel @Inject constructor(
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

    var weatherDisposable: Disposable? = null
    var disposable: CompositeDisposable = CompositeDisposable()

    private fun loadWeather(query: String) {
        weatherDisposable = getWeatherUseCase(query)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _loading.value = true }
            .doAfterTerminate { _loading.value = false }
            .subscribeBy(onSuccess = { weatherInfo ->
                _weatherInfo.value = weatherInfo
            }, onError = { error ->
                _error.postValue(error)
            })

//        viewModelScope.launch {
//            try {
//                _loading.value = true
//                getWeatherUseCase(query).also { weatherInfo ->
//                    _weatherInfo.value = weatherInfo
//                }
//            } catch (error: Throwable) {
//                _error.value = error
//            } finally {
//                _loading.value = false
//            }
//        }
    }

    override fun onCleared() {
        super.onCleared()
        weatherDisposable?.dispose()
        disposable.clear()
    }

    companion object {
//        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
//            @Suppress("UNCHECKED_CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//                extras: CreationExtras
//            ): T {
//                val useCase = DataContainer.weatherUseCase
        // Create a SavedStateHandle for this ViewModel from extras
//                val savedStateHandle = extras.createSavedStateHandle()
//                return MainViewModel(useCase) as T
//            }
//        }

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