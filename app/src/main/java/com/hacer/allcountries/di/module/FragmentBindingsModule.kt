package com.hacer.allcountries.di.module

import com.hacer.allcountries.view.CountriesFragment
import com.hacer.allcountries.view.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingsModule {
    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindCountriesFragment(): CountriesFragment
}