package com.itis.template

import android.app.Application
import com.itis.template.di.dagger.AppComponent
import com.itis.template.di.DaggerAppComponent
import com.itis.template.di.koin.appModule
import com.itis.template.di.koin.featureModule
import com.itis.template.di.koin.networkModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.hilt.android.HiltAndroidApp
import org.kodein.di.DI
import org.kodein.di.DIAware
import org.kodein.di.android.x.androidXModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

@HiltAndroidApp
class App : Application(), HasAndroidInjector, DIAware {

    override val di by DI.lazy {
        /* bindings */
        importAll(
            androidXModule(this@App),
            com.itis.template.di.kodein.appModule,
            com.itis.template.di.kodein.networkModule,
            com.itis.template.di.kodein.featureModule,
        )
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        appComponent = DaggerAppComponent.builder()
            .context(applicationContext)
            .build().apply {
                inject(this@App)
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

    companion object {

        lateinit var appComponent: AppComponent
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }
}
