package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import com.example.cryptocurrencyapp.viewmodel.RepoListViewModel
import dagger.Component
import dagger.Provides

@Component(modules = [ApiModule::class])
interface ViewModelComponent {

    fun inject(viewModel: CoinListViewModel)

    fun inject(viewModel: RepoListViewModel)
}