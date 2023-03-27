package com.itis.template.di

import android.content.Context
import com.itis.template.presentation.mvvm.auth.AuthComponent
import com.itis.template.presentation.mvvm.main.MainComponent
import com.itis.template.utils.ResourceProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class, WeatherModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {

    fun provideContext(): Context

    fun provideResourceProvider(): ResourceProvider

    fun plusMainComponent(): MainComponent.Builder

    fun plusAuthComponent(): AuthComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent
    }

}