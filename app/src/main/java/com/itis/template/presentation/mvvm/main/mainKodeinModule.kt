package com.itis.template.presentation.mvvm.main

import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.instance

val mainKodeinModule = DI.Module("mainKodeinModule") {
    bindProvider {
        MainViewModel(instance())
    }
}
