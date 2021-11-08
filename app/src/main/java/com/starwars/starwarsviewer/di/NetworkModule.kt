package com.starwars.starwarsviewer.di

import androidx.viewbinding.BuildConfig
import com.starwars.starwarsviewer.network.planet.PlanetsService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {
    companion object {
        private const val BASE_URL = "https://swapi.dev/"
    }

    @Provides
    @Named(BASE_URL)
    fun provideBaseUrlString() = BASE_URL

    @Provides
    @Singleton
    fun providePlanetService(retrofit: Retrofit): PlanetsService = retrofit.create(PlanetsService::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, @Named(BASE_URL) baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    fun provideHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return if (BuildConfig.DEBUG) {
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        } else {
            OkHttpClient.Builder().build()
        }
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}