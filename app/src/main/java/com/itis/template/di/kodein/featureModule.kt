package com.itis.template.di.kodein

import com.itis.template.presentation.mvvm.main.mainKodeinModule
import org.kodein.di.DI

val featureModule = DI.Module("featureModule") {
    importAll(
        mainKodeinModule,
//        authModule,
    )
}
