package com.itis.template.presentation.mvvm.main

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainKoinModule = module {
    viewModel { MainViewModel(get()) }
}
