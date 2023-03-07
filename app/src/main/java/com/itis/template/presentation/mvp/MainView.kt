package com.itis.template.presentation.mvp

interface MainView {

    fun hideKeyboard()

    fun showLoading(isShow: Boolean)

    fun showError(throwable: Throwable)

    fun showTemp(temp: Double)

    fun showWeatherIcon(id: String)

    fun navigateToDetails(id: String)
}