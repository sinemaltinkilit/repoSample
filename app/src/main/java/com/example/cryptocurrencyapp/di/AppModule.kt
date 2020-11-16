package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.ListFragment
import com.example.cryptocurrencyapp.MainActivity
import com.example.cryptocurrencyapp.MyApplication
import com.example.cryptocurrencyapp.DetailFragment
import com.example.cryptocurrencyapp.view.RepoListFragment
import com.example.cryptocurrencyapp.viewmodel.CoinListViewModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    //fun bindMainActivity(): MainActivity

    @Provides
    @AppScope
    fun provideActivity(mainActivity: MainActivity): MainActivity = mainActivity

    @Provides
    @AppScope
    fun provideCoinListViewModel(coinListViewModel: CoinListViewModel): CoinListViewModel = coinListViewModel

    @Provides
    @AppScope
    fun provideApplication(application: MyApplication): MyApplication = application

    @Provides
    @AppScope
    fun provideFirstFragment(listFragment: ListFragment): ListFragment = listFragment

    @Provides
    @AppScope
    fun provideRepoListFragment(repoListFragment: RepoListFragment): RepoListFragment = repoListFragment

    @Provides
    @AppScope
    fun provideSecondFragment(detailFragment: DetailFragment): DetailFragment = detailFragment
}