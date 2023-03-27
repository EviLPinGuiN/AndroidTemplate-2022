package com.itis.template.presentation.mvvm.auth

import androidx.lifecycle.ViewModel
import com.itis.template.di.FeatureScope
import com.itis.template.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ArgCityId

@Module
interface AuthModule {

    @Binds
    @FeatureScope
    @[IntoMap ViewModelKey(AuthViewModel::class)]
    fun provideViewModel(viewModel: AuthViewModel): ViewModel
}
