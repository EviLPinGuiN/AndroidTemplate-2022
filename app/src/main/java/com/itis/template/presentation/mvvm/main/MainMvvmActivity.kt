package com.itis.template.presentation.mvvm.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import coil.load
import com.itis.template.databinding.ActivityWeatherBinding
import com.itis.template.utils.showSnackbar
import dagger.android.support.DaggerAppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.closestDI
import org.kodein.di.android.x.viewmodel.viewModel
import org.koin.android.ext.android.inject
import timber.log.Timber
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @MainThread
    inline fun <reified VM : ViewModel> ComponentActivity.viewModels(
        noinline factoryProducer: (() -> ViewModelProvider.Factory) = { factory },
        noinline extrasProducer: (() -> CreationExtras)? = null
    ): Lazy<VM> {
        return viewModels(extrasProducer, factoryProducer)
    }
}

@AndroidEntryPoint
class MainMvvmActivity : DaggerAppCompatActivity(), DIAware {

    private var binding: ActivityWeatherBinding? = null

    override val di: DI by closestDI()

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    private val viewModel: MainViewModel by viewModels { factory }
    private val viewModelKoin: MainViewModel by inject()
    private val viewModelKodein: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
//        App.appComponent.plusMainComponent()
//            .build()
//            .inject(this)
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
        observeViewModel()
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