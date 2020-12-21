package com.hacer.allcountries.adapter

import android.view.View
import dagger.Component

@Component
interface ItemClickListener {
    fun onCountryClicked(v: View)
}