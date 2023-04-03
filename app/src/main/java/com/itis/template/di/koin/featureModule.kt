package com.itis.template.di.koin

import com.itis.template.presentation.mvvm.main.mainKoinModule
import org.koin.dsl.module

val featureModule = module {
    includes(
        mainKoinModule,
//        authModule,
    )
}
