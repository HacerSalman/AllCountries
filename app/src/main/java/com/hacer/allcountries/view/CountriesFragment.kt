package com.hacer.allcountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hacer.allcountries.R
import com.hacer.allcountries.databinding.FragmentCountriesBinding
import com.hacer.allcountries.viewmodel.CountryViewModel
import com.hacer.allcountries.viewmodel.HomeViewModel
import com.hacer.allcountries.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_countries.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.item_country.countryName
import kotlinx.android.synthetic.main.item_country.countryRegion
import javax.inject.Inject

class CountriesFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(CountryViewModel::class.java)
    }
    private lateinit var dataBinding : FragmentCountriesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_countries,container,false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
         var countryName = ""
        arguments?.let {
            countryName = CountriesFragmentArgs.fromBundle(it).countryName
        }
        viewModel.getCountryDetails(countryName)
        observeLiveData()
    }

    fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer{
            it?.let{
                dataBinding.country = it
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it) {
                    progressBarCountry.visibility = View.VISIBLE

                } else {
                    progressBarCountry.visibility = View.GONE
                }
            }
        })
    }
}