package com.itis.template.di.koin

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.itis.template.utils.AndroidResourceProvider
import com.itis.template.utils.ResourceProvider
import org.koin.dsl.module

val appModule = module {
    factory<ResourceProvider> {
        AndroidResourceProvider(get())
    }
    single<FusedLocationProviderClient> {
        LocationServices.getFusedLocationProviderClient(get())
    }
}
