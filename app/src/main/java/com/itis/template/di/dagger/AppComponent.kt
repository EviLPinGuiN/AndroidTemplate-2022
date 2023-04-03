package com.itis.template.di.dagger

import android.content.Context
import com.itis.template.App
import com.itis.template.utils.ResourceProvider
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        WeatherModule::class,
        ViewModelModule::class,
        FeatureModule::class,
    ]
)
@Singleton
interface AppComponent {

    fun provideContext(): Context

    fun provideResourceProvider(): ResourceProvider

//    fun plusMainComponent(): MainComponent.Builder

//    fun plusAuthComponent(): AuthComponent.Builder

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(applicationContext: Context): Builder

        fun build(): AppComponent
    }

    fun inject(application: App)
}