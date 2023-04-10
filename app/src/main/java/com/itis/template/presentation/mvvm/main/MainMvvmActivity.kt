package com.itis.template.presentation.mvvm.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import coil.load
import com.itis.template.databinding.ActivityWeatherBinding
import com.itis.template.utils.showSnackbar
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.Flowables
import io.reactivex.rxjava3.kotlin.subscribeBy
import org.koin.android.ext.android.inject
import timber.log.Timber
import java.util.concurrent.TimeUnit
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

class MainMvvmActivity : AppCompatActivity() {

    private var binding: ActivityWeatherBinding? = null

    private val viewModel: MainViewModel by inject()

    private var searchDisposable: Disposable? = null

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

            searchDisposable = etCity.observeQuery()
                .filter { it.length > 2 }
                .debounce(500L, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onNext = {
                    Log.e("SEARCH QUERY", it)
                    viewModel.onLoadClick(it)
                }, onError = {
                    Log.e("Error", it.toString())
                })
        }
        observeViewModel()
    }

    override fun onDestroy() {
        super.onDestroy()
        searchDisposable?.dispose()
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

    private fun EditText.observeQuery() =
        Flowables.create<String>(mode = BackpressureStrategy.LATEST) { emitter ->
            addTextChangedListener {
                emitter.onNext(it.toString())
            }
        }
}
