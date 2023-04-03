package com.itis.template.di.dagger

import com.itis.template.BuildConfig
import com.itis.template.data.core.interceptor.ApiKeyInterceptor
import com.itis.template.data.weather.datasource.remote.WeatherApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InterceptApiKey

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InterceptLogger

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @InterceptLogger
    fun provideLoggingInterceptor(): Interceptor = HttpLoggingInterceptor().apply {
        level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @InterceptApiKey
    fun provideApiKeyInterceptor(): Interceptor = ApiKeyInterceptor()

    @Provides
    fun provideHttpClient(
        @InterceptApiKey apiKeyInterceptor: Interceptor,
        @InterceptLogger loggingInterceptor: Interceptor,
    ): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(apiKeyInterceptor)
        .connectTimeout(10L, TimeUnit.SECONDS)
        .build()

    @Provides
    fun provideRetrofit(
        httpClient: OkHttpClient,
        gsonFactory: GsonConverterFactory,
        @Named("base_url") baseUrl: String,
    ): Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(baseUrl)
        .addConverterFactory(gsonFactory)
        .build()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    fun provideWeatherApi(
        retrofit: Retrofit
    ): WeatherApi = retrofit.create(WeatherApi::class.java)

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String = BuildConfig.API_ENDPOINT

    @Provides
    @Named("app_name")
    fun provideAppName(): String = "WeatherApp"
}