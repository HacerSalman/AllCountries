package com.hacer.allcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hacer.allcountries.model.Country
import com.hacer.allcountries.network.CountryService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class CountryViewModel: ViewModel() {
    val countryLiveData = MutableLiveData<Country>()
    private val disposable = CompositeDisposable()
    private val countryApiService = CountryService()
    val loading = MutableLiveData<Boolean>()

    fun getCountryDetails(countryName:String?) {
        loading.value = true
        disposable.add(
            countryApiService.getCountryDetails(countryName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        countryLiveData.value = t.first()
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        e.printStackTrace()
                    }

                })
        )

    }


}