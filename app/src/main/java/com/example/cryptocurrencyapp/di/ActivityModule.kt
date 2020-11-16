package com.example.cryptocurrencyapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencyapp.MainActivity
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import com.example.cryptocurrencyapp.viewmodel.RepoListViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap
import javax.inject.Scope

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindCoinListViewModel(): CoinListViewModel

    @ContributesAndroidInjector
    abstract fun bindRepoListViewModel(): RepoListViewModel

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    //@Binds
    //abstract fun bindsViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}