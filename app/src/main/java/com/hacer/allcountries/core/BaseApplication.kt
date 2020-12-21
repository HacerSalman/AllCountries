package com.hacer.allcountries.core

import com.hacer.allcountries.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

 class BaseApplication: DaggerApplication() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        val appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this)
        return appComponent
    }
}
