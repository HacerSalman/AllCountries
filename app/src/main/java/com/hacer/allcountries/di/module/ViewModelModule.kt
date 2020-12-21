package com.hacer.allcountries.di.module

import androidx.lifecycle.ViewModel
import com.hacer.allcountries.viewmodel.CountryViewModel
import com.hacer.allcountries.viewmodel.HomeViewModel
import com.hacer.allcountries.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(homeViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountryViewModel::class)
    abstract fun bindCountryViewModel(countryViewModel: CountryViewModel): ViewModel
}