package com.example.comcentriclowes.data


import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("city")
    val city: City? = null,
    @SerializedName("cnt")
    val cnt: Int? = null,
    @SerializedName("cod")
    val cod: String? = null,
    @SerializedName("list")
    val list: List<Forecast>? = null,
    @SerializedName("message")
    val message: Int? = null
)