package com.itis.template.di

import androidx.lifecycle.ViewModelProvider
import com.itis.template.utils.AppViewModelFactory
import dagger.Binds
import dagger.Module

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(
        factory: AppViewModelFactory
    ): ViewModelProvider.Factory
}
