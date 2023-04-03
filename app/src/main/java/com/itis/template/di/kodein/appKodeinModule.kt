package com.itis.template.di.kodein

import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.itis.template.utils.AndroidResourceProvider
import com.itis.template.utils.ResourceProvider
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val appModule = DI.Module("appModule") {
    bindProvider<ResourceProvider> {
        AndroidResourceProvider(instance())
    }
    bindSingleton<FusedLocationProviderClient> {
        LocationServices.getFusedLocationProviderClient(instance())
    }
}
