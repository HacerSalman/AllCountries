package com.hacer.allcountries.network

import com.hacer.allcountries.model.Country
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class CountryService @Inject constructor() {
    private val BASE_URL = "https://restcountries.eu/rest/v2/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CountryServiceInterface::class.java)

    fun getCountryList() : Single<List<Country>> {
        return api.getCountries()
    }

    fun getCountryDetails(countryName:String?) : Single<List<Country>> {
        return api.getCountryDetails(countryName)
    }
}