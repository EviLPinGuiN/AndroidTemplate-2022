package com.itis.template.presentation.moxy

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution
import moxy.viewstate.strategy.alias.Skip

@AddToEndSingle
interface MainMoxyView : MvpView {

    @OneExecution
    fun hideKeyboard()

    fun showLoading(isShow: Boolean)

    @Skip
    fun showError(throwable: Throwable)

    fun showTemp(temp: Double)

    fun showWeatherIcon(id: String)

    @OneExecution
    fun navigateToDetails(id: String)
}