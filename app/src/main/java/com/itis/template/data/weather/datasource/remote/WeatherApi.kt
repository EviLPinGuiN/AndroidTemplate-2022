package com.itis.template.data.weather.datasource.remote

import com.itis.template.data.weather.datasource.remote.response.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface WeatherApi {

    @GET("weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
    ): Single<WeatherResponse>

    @GET("weather")
    fun getWeather(
        @QueryMap map: Map<String, Any>
    ): Single<WeatherResponse>
}
