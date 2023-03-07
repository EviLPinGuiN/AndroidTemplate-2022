package com.itis.template.presentation.moxy

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import coil.load
import com.itis.template.databinding.ActivityWeatherBinding
import com.itis.template.di.DataContainer
import com.itis.template.utils.hideKeyboard
import com.itis.template.utils.showSnackbar
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter
import timber.log.Timber

class MainMoxyActivity : MvpAppCompatActivity(), MainMoxyView {

    private var binding: ActivityWeatherBinding? = null

    private val presenter: MainMoxyPresenter by moxyPresenter {
        MainMoxyPresenter(getWeatherUseCase = DataContainer.weatherUseCase)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // from binding
        binding = ActivityWeatherBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.run {
            btnLoad.setOnClickListener {
                presenter.onLoadClick(etCity.text.toString())
            }
            etCity.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter.onSearchClick(etCity.text.toString())
                }
                true
            }
        }
    }

    override fun hideKeyboard() {
        binding?.etCity?.hideKeyboard()
    }

    override fun showLoading(isShow: Boolean) {
        binding?.progress?.isVisible = isShow
    }

    override fun showError(error: Throwable) {
        findViewById<View>(android.R.id.content)
            .showSnackbar(error.message ?: "Error")
    }

    override fun showTemp(temp: Double) {
        binding?.tvTemp?.run {
            text = "Temp: $temp C"
            isVisible = true
        }
    }

    override fun showWeatherIcon(id: String) {
        Timber.e(id)
        binding?.ivIcon?.load("https://openweathermap.org/img/w/$id.png") {
            crossfade(true)
        }
    }

    override fun navigateToDetails(id: String) {
        // intent, supportfm, jetpack navigation
    }
}
