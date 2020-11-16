package com.example.cryptocurrencyapp.model

import com.example.cryptocurrencyapp.data.CoinsDataModel
import com.example.cryptocurrencyapp.data.ExchangeRatesModel
import com.example.cryptocurrencyapp.data.RepoModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AppApi {

    @GET("exchange_rates")
    fun getCoinsExchangeRates(): Single<List<ExchangeRatesModel>>

    @GET("search/trending")
    fun getTrendCoins(): Single<CoinsDataModel>

    @GET("{user}/repos")
    fun getRepos(@Path("user") user: String?): Single<List<RepoModel>>
}