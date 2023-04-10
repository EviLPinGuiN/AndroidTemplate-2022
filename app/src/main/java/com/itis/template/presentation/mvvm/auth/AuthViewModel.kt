package com.itis.template.presentation.mvvm.auth

import androidx.lifecycle.ViewModel
import com.itis.template.domain.auth.LoginUseCase

class AuthViewModel(
    private val cityId: Int,
    private val loginUseCase: LoginUseCase,
) : ViewModel() {

}