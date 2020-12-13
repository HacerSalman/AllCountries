package com.hacer.allcountries.model

import com.google.gson.annotations.SerializedName

data class Country (
    @SerializedName("numericCode")
    val numericCode: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("region")
    val region: String?,

    @SerializedName("capital")
    val capital: String?,

    @SerializedName("population")
    val population: Long?,

    @SerializedName("flag")
    val flag: String)

