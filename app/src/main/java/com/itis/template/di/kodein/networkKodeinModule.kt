package com.itis.template.di.kodein

import com.itis.template.BuildConfig
import com.itis.template.data.core.interceptor.ApiKeyInterceptor
import com.itis.template.data.weather.datasource.remote.WeatherApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = DI.Module("networkModule") {
    bindProvider<Interceptor>(tag = "ApiKey") {
        ApiKeyInterceptor()
    }
    bindProvider<Interceptor>(tag = "Logging") {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }
    bindSingleton { GsonConverterFactory.create() }
    bindSingleton(tag = "base_url") { BuildConfig.API_ENDPOINT }
    bindSingleton {
        provideHttpClient(instance(tag = "ApiKey"), instance(tag = "Logging"))
    }
    bindSingleton {
        provideRetrofit(
            httpClient = instance(),
            gsonFactory = instance(),
            baseUrl = instance(tag = "base_url")
        )
    }
    bindProvider {
        instance<Retrofit>().create(WeatherApi::class.java)
    }
}

private fun provideHttpClient(
    apiKeyInterceptor: Interceptor,
    loggingInterceptor: Interceptor,
): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .addInterceptor(apiKeyInterceptor)
    .connectTimeout(10L, TimeUnit.SECONDS)
    .build()

private fun provideRetrofit(
    httpClient: OkHttpClient,
    gsonFactory: GsonConverterFactory,
    baseUrl: String,
): Retrofit = Retrofit.Builder()
    .client(httpClient)
    .baseUrl(baseUrl)
    .addConverterFactory(gsonFactory)
    .build()