package com.example.cryptocurrencyapp.model

import com.example.cryptocurrencyapp.data.CoinsDataModel
import com.example.cryptocurrencyapp.data.ExchangeRatesModel
import com.example.cryptocurrencyapp.data.RepoModel
import com.example.cryptocurrencyapp.di.DaggerAppComponent
import io.reactivex.Single
import javax.inject.Inject

class AppApiService {

    @Inject
    lateinit var api: AppApi

    init {
        DaggerAppComponent.create().inject(this)
    }

    fun getCoinList(): Single<List<ExchangeRatesModel>> {
        return api.getCoinsExchangeRates()
    }

    fun getTrendCoinList(): Single<CoinsDataModel> {
        return api.getTrendCoins()
    }

    fun getRepoList(user: String): Single<List<RepoModel>> {
        return api.getRepos(user)
    }
}