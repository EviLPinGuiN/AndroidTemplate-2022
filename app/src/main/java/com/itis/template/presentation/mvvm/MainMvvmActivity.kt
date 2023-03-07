package com.itis.template.presentation.mvvm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.itis.template.databinding.ActivityWeatherBinding
import com.itis.template.utils.showSnackbar
import timber.log.Timber

class MainMvvmActivity : AppCompatActivity() {

    private var binding: ActivityWeatherBinding? = null

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.Factory
    }

    // для кейсов, когда viewmodel имеет пустой конструктор
    private val viewModel2: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // from binding
        binding = ActivityWeatherBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        binding?.run {
            btnLoad.setOnClickListener {
                viewModel.onLoadClick(etCity.text.toString())
            }
            etCity.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.onLoadClick(etCity.text.toString())
                }
                true
            }
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            loading.observe(this@MainMvvmActivity) {
                binding?.progress?.isVisible = it
            }
            weatherInfo.observe(this@MainMvvmActivity) {
                if (it == null) return@observe
                showTemp(it.temperature)
                showWeatherIcon(it.icon)
            }
            error.observe(this@MainMvvmActivity) {
                if (it == null) return@observe
                showError(it)
            }
            navigateDetails.observe(this@MainMvvmActivity) {
                if (it == true) {
                    startActivity(Intent())
                    navigateDetails.value = null
                }
            }
        }
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
