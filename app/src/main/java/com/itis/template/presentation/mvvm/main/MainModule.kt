package com.itis.template.presentation.mvvm.main

import androidx.lifecycle.ViewModel
import com.itis.template.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainModule {

    @Binds
    @[IntoMap ViewModelKey(MainViewModel::class)]
    fun provideViewModel(viewModel: MainViewModel): ViewModel
}