package com.example.cryptocurrencyapp.data

data class ExchangeRatesModel(
    val rates: List<CoinDataModel>
)

data class CoinDataModel(
    val name: String?,
    val unit: String?,
    val value: Double?,
    val type: String
)