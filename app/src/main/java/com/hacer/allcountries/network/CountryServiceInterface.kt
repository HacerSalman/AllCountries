package com.hacer.allcountries.network

import com.hacer.allcountries.model.Country
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface CountryServiceInterface {
    @GET("all")
    fun getCountries(): Single<List<Country>>

    @GET("name/{countryName}")
    fun getCountryDetails(@Path("countryName") countryName:String?): Single<List<Country>>
}