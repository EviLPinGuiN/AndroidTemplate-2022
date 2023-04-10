package com.itis.template

import android.app.Application
import com.itis.template.di.koin.appModule
import com.itis.template.di.koin.featureModule
import com.itis.template.di.koin.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        startKoin {
            androidContext(this@App)
            modules(
                appModule,
                networkModule,
                featureModule
            )
        }
    }
}
