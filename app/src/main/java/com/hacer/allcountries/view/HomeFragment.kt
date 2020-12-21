package com.hacer.allcountries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.hacer.allcountries.adapter.CountryAdapter
import com.hacer.allcountries.R
import com.hacer.allcountries.viewmodel.HomeViewModel
import com.hacer.allcountries.viewmodel.ViewModelFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class HomeFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)
    }
    private var adapter = CountryAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
      return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.refreshData()

        recyclerViewHome.layoutManager = LinearLayoutManager(context)
        recyclerViewHome.adapter = adapter

        swipeRefreshLayout.setOnRefreshListener {
            recyclerViewHome.visibility = View.GONE
            errorMessageHome.visibility = View.GONE
            progressBarHome.visibility = View.VISIBLE
            viewModel.refreshData()
        }

        observeLiveData()

    }
    private fun observeLiveData() {
        viewModel.countryList.observe(viewLifecycleOwner, Observer {countries ->

            countries?.let {
                recyclerViewHome.visibility = View.VISIBLE
                adapter.updateCountryList(countries)
            }

        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { error->
            error?.let {
                if(it) {
                    errorMessageHome.visibility = View.VISIBLE
                } else {
                    errorMessageHome.visibility = View.GONE
                }
            }
        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading->
            loading?.let {
                if (it) {
                    progressBarHome.visibility = View.VISIBLE
                    recyclerViewHome.visibility = View.GONE
                    errorMessageHome.visibility = View.GONE
                } else {
                    progressBarHome.visibility = View.GONE
                }
            }
        })
    }


}