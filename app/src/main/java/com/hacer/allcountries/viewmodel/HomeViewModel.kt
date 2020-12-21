package com.hacer.allcountries.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hacer.allcountries.model.Country
import com.hacer.allcountries.network.CountryService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeViewModel @Inject constructor() : ViewModel() {
    val countryList = MutableLiveData<List<Country>>()
    val errorMessage = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    @Inject
    lateinit var countryApiService: CountryService

     fun refreshData() {
        loading.value = true

        disposable.add(
            countryApiService.getCountryList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Country>>(){
                    override fun onSuccess(t: List<Country>) {
                        countryList.value = t
                        errorMessage.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable) {
                        loading.value = false
                        errorMessage.value = true
                        e.printStackTrace()
                    }

                })
        )
    }

}


