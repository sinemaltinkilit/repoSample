package com.example.cryptocurrencyapp.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptocurrencyapp.data.RepoModel
import com.example.cryptocurrencyapp.di.DaggerViewModelComponent
import com.example.cryptocurrencyapp.model.AppApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoListViewModel: ViewModel() {

    @Inject
    lateinit var service: AppApiService
    private val disposable = CompositeDisposable()

    var dataList: MutableLiveData<List<RepoModel>> = MutableLiveData()
    var showProgress: MutableLiveData<Int> = MutableLiveData(View.GONE)

    init {
        DaggerViewModelComponent.create().inject(this)
    }

    fun getRepoList(user: String?) {
        user?.let {
            showProgress.postValue(View.VISIBLE)
            disposable.add(service.getRepoList(it)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableSingleObserver<List<RepoModel>>() {
                    override fun onSuccess(repoDetail: List<RepoModel>) {
                        showProgress.postValue(View.GONE)
                        handleResponse(repoDetail)
                    }

                    override fun onError(e: Throwable) {
                        showProgress.postValue(View.GONE)
                    }
                }))
        }
    }

    fun handleResponse(repo: List<RepoModel>) {
        dataList.postValue(repo)
    }
}