package com.hacer.allcountries.di.module

import com.hacer.allcountries.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingsModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity
}