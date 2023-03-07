package com.itis.template.presentation.mvp

import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.AsyncListUtil.DataCallback
import coil.load
import com.itis.template.App
import com.itis.template.di.DataContainer
import com.itis.template.databinding.ActivityWeatherBinding
import com.itis.template.domain.weather.GetWeatherUseCase
import com.itis.template.presentation.mvp.MainView
import com.itis.template.utils.hideKeyboard
import com.itis.template.utils.showSnackbar
import kotlinx.coroutines.launch
import timber.log.Timber

class MainMvpActivity : AppCompatActivity(), MainView {

    private var binding: ActivityWeatherBinding? = null
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initPresenter()
        // from binding
        binding = ActivityWeatherBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }
        binding?.run {
            btnLoad.setOnClickListener {
                presenter?.onLoadClick(etCity.text.toString())
            }
            etCity.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    presenter?.onSearchClick(etCity.text.toString())
                }
                true
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onClear()
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

    private fun initPresenter() {


        presenter = MainPresenter(
            view = this,
            getWeatherUseCase = DataContainer.weatherUseCase
        )
    }
}
