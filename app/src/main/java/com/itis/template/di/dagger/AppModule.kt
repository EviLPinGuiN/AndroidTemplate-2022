package com.itis.template.di.dagger

import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.itis.template.utils.AndroidResourceProvider
import com.itis.template.utils.ResourceProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideResourceProvider(
        context: Context
    ): ResourceProvider = AndroidResourceProvider(context)

    @Provides
    fun provideFusedLocationClient(
        context: Context
    ): FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
}