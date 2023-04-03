package com.itis.template.presentation

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import coil.load
import com.itis.template.App
import com.itis.template.databinding.ActivityWeatherBinding
import com.itis.template.domain.weather.GetWeatherUseCase
import com.itis.template.utils.hideKeyboard
import com.itis.template.utils.showSnackbar
import kotlinx.coroutines.launch
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private var binding: ActivityWeatherBinding? = null

//    @Inject
    lateinit var getWeatherUseCase: GetWeatherUseCase
//    private val getWeatherUseCase: GetWeatherUseCase = DataContainer.weatherUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        App.appComponent.provideResourceProvider()
        super.onCreate(savedInstanceState)
        // from binding
        binding = ActivityWeatherBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.run {
            btnLoad.setOnClickListener {
                onLoadClick()
            }
            etCity.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    onLoadClick()
                }
                true
            }
        }
    }

    private fun onLoadClick() {
        binding?.run {
            etCity.hideKeyboard()
            loadWeather(etCity.text.toString())
        }
    }

    private fun loadWeather(query: String) {
        lifecycleScope.launch {
            try {
                showLoading(true)
                getWeatherUseCase(query).also { weatherInfo ->
                    showTemp(weatherInfo.temperature)
                }

//                api.getWeather(
//                    mapOf(
//                        "lat" to 10.0,
//                        "lon" to 22.2,
//                        "units" to "metric",
//                        "appid" to "API_KEY",
//                        "lang" to "ru"
//                    )
//                )
            } catch (error: Throwable) {
                showError(error)
            } finally {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isShow: Boolean) {
        binding?.progress?.isVisible = isShow
    }

    private fun showError(error: Throwable) {
        findViewById<View>(android.R.id.content)
            .showSnackbar(error.message ?: "Error")
    }

    private fun showTemp(temp: Double) {
        binding?.tvTemp?.run {
            text = "Temp: $temp C"
            isVisible = true
        }
    }

    private fun showWeatherIcon(id: String) {
        Timber.e(id)
        binding?.ivIcon?.load("https://openweathermap.org/img/w/$id.png") {
            crossfade(true)
        }
    }
}
