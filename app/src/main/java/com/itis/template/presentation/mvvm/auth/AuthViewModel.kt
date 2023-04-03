package com.itis.template.presentation.mvvm.auth

import androidx.lifecycle.ViewModel
import com.itis.template.domain.auth.LoginUseCase
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    @ArgCityId private val cityId: Int,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

}