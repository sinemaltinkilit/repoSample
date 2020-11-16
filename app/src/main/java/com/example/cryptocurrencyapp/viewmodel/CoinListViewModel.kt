package com.example.cryptocurrencyapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.databinding.ObservableField
import com.example.cryptocurrencyapp.data.ExchangeRatesModel
import com.example.cryptocurrencyapp.di.DaggerViewModelComponent
import com.example.cryptocurrencyapp.model.AppApiService
import com.example.cryptocurrencyapp.data.CoinsDataModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CoinListViewModel(application: Application): AndroidViewModel(application) {

    val coinList = MutableLiveData<CoinsDataModel>()
    var coinListDataObservable = ObservableField<CoinsDataModel>()
    var coinName = MutableLiveData<String>()
    var coinValue = ObservableField<String>()
    val loading = MutableLiveData<Boolean>()
    val isError = MutableLiveData<Boolean>()

    private val disposable = CompositeDisposable()
    @Inject
    lateinit var service: AppApiService

    init {
        DaggerViewModelComponent.create().inject(this)
    }

    fun getCoinData() : Single<List<ExchangeRatesModel>>? {
        return service.getCoinList()
    }

    fun getTrendCoinData(): Single<CoinsDataModel> {
        return service.getTrendCoinList()
    }

    fun refresh() {
        loading.value = true
        getList()
    }

    fun getList() {
        disposable.add(service.getTrendCoinList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object: DisposableSingleObserver<CoinsDataModel>() {
                override fun onSuccess(list: CoinsDataModel) {
                    System.out.println("onResponse");
                    System.out.println(list);
                    handleResponse(list)
                }

                override fun onError(e: Throwable) {
                    isError.value = true
                    coinList.value = null
                    loading.value = false
                }

            }))
    }

    fun handleResponse(list: CoinsDataModel) {
        isError.value = false
        //coinName.value = list.get(0).coins.get(0).item.name
        //coinValue.value = list.get(0).coins.get(0).item.id
        coinListDataObservable.set(list)
        coinList.value = list
        loading.value = false
    }
}