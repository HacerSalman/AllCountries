package com.hacer.allcountries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.hacer.allcountries.R
import com.hacer.allcountries.databinding.ItemCountryBinding
import com.hacer.allcountries.model.Country
import com.hacer.allcountries.view.HomeFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(private val countryList : ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.ViewHolder>(),ItemClickListener  {
    class ViewHolder(val view: ItemCountryBinding):RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater,R.layout.item_country,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
      return countryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this
    }

    fun updateCountryList(newList:List<Country>){
        countryList.clear()
        countryList.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val countryName = v.countryName.text.toString()
        val action = HomeFragmentDirections.actionHomeFragmentToCountriesFragment(countryName)
        //action.countryUuid
        Navigation.findNavController(v).navigate(action)

    }

}