package com.example.comcentriclowes.rest

import com.example.comcentriclowes.data.Forecast
import com.example.comcentriclowes.util.UIState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface WeatherRepository {
    fun getForecast(city: String): Flow<UIState<List<Forecast>>>
}

class WeatherRepositoryImpl @Inject constructor(
    private val serviceAPI: WeatherAPI,
    private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {
    override fun getForecast(city: String): Flow<UIState<List<Forecast>>> = flow {
        emit(UIState.LOADING)

        try {
            val response = serviceAPI.getWeather(city)
            if (response.isSuccessful) {
                response.body()?.let {
                    emit(UIState.SUCCESS(it.list ?: emptyList()))
                } ?: throw Exception("Response is null")
            } else {
                throw Exception(response.errorBody()?.string())
            }
        } catch (e: Exception) {
            emit(UIState.ERROR(e))
        }
    }.flowOn(ioDispatcher)

}