package com.example.comcentriclowes.util

import kotlin.math.roundToInt

// API: https://api.openweathermap.org/data/2.5/forecast?q={city}&appid={api key}
//
//
//
//You can use API key: 65d00499677e59496ca2f318eb68c049

const val BASE_URL = "https://api.openweathermap.org/"
const val FORECAST_PATH = "data/2.5/forecast"
const val API_KEY = "65d00499677e59496ca2f318eb68c049"
const val KELVIN_TEMP = 273.15

fun Double?.convertKelvinToFahrenheit(): String {
    return this?.let {
        ((it - KELVIN_TEMP) * (9/5) + 32).roundToInt().toString()
    } ?: " ---- "
}