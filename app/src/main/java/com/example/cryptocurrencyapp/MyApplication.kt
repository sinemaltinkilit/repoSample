package com.example.cryptocurrencyapp

import android.app.Application

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        /*
        DaggerAppComponent.builder()
            .application(this)
            .build().inject(this)*/
    }
}