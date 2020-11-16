package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.model.AppApi
import com.example.cryptocurrencyapp.model.AppApiService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://api.github.com/users/" //"https://api.coingecko.com/api/v3/"

    @Provides
    fun provideCoinsApi(): AppApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(AppApi::class.java)
    }

    @Provides
    fun provideCoinsApiService(): AppApiService {
        return AppApiService()
    }

}