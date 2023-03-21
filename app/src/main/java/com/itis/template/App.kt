package com.itis.template

import android.app.Application
import com.itis.template.di.AppComponent
import com.itis.template.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build()
    }

    companion object {

        lateinit var appComponent: AppComponent
    }
}
