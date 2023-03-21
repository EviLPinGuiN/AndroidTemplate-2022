package com.itis.template.di

import android.content.Context
import com.itis.template.presentation.MainActivity
import com.itis.template.utils.ResourceProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class, WeatherModule::class])
@Singleton
interface AppComponent {

    fun provideContext(): Context

    fun provideResourceProvider(): ResourceProvider

    fun inject(weatherActivity: MainActivity)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent
    }

}