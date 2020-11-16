package com.example.cryptocurrencyapp.di

import com.example.cryptocurrencyapp.MyApplication
import com.example.cryptocurrencyapp.model.AppApiService
import dagger.Component
import dagger.android.AndroidInjectionModule

@Component(modules = [ApiModule::class, AppModule::class, ActivityModule::class, AndroidInjectionModule::class, ViewModelModule::class])
interface AppComponent {

    /*
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder
        fun build(): AppComponent
    }*/

    // @BindsInstance
    //fun application(application: MyApplication): DaggerAppComponent.Builder

    /*Application inject*/
    fun inject(application: MyApplication)

    fun inject(service: AppApiService)

    //fun inject(mainActivity: MainActivity)
}