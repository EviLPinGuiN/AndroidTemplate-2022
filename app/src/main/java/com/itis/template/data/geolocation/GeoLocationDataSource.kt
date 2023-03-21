@file:OptIn(ExperimentalCoroutinesApi::class)

package com.itis.template.data.geolocation

import android.annotation.SuppressLint
import com.google.android.gms.location.FusedLocationProviderClient
import com.itis.template.domain.GeoLocation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class GeoLocationDataSource @Inject constructor(
    val client: FusedLocationProviderClient
) {

    @SuppressLint("MissingPermission")
    suspend fun getLastLocation(): GeoLocation = suspendCancellableCoroutine<GeoLocation> { emitter ->
        client.lastLocation
            .addOnSuccessListener {
                emitter.resume(
                    GeoLocation(
                        lon = it.longitude,
                        lat = it.latitude
                    )
                )
            }
            .addOnFailureListener {
                emitter.resumeWithException(it)
            }
    }

    @SuppressLint("MissingPermission")
    suspend fun getLastLocationV2(): GeoLocation = client.lastLocation.await().let {
        GeoLocation(
            lon = it.longitude,
            lat = it.latitude
        )
    }
}