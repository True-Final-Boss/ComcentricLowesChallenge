package com.example.comcentriclowes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comcentriclowes.data.Forecast
import com.example.comcentriclowes.rest.WeatherRepository
import com.example.comcentriclowes.util.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _forecast :MutableLiveData<UIState<List<Forecast>>> = MutableLiveData(UIState.LOADING)
    val forecast: LiveData<UIState<List<Forecast>>> get() = _forecast

    var searchCity: String? = null
    var selectedForecast: Forecast? = null

    fun getCityForecast() {
        searchCity?.let {
            viewModelScope.launch {
                repository.getForecast(it).collect {
                    _forecast.postValue(it)
                }
            }
        } ?: _forecast.postValue(UIState.ERROR(Exception("City not selected")))
    }
}