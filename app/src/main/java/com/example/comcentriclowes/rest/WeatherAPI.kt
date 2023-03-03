package com.example.comcentriclowes.rest

import com.example.comcentriclowes.data.WeatherResponse
import com.example.comcentriclowes.util.API_KEY
import com.example.comcentriclowes.util.FORECAST_PATH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET(FORECAST_PATH)
    suspend fun getWeather(
        @Query("q") city: String,
        @Query("appid") key: String = API_KEY
    ): Response<WeatherResponse>
}