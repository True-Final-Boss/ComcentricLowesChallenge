package com.example.comcentriclowes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import com.example.comcentriclowes.data.Forecast
import com.example.comcentriclowes.databinding.FragmentDetailsBinding
import com.example.comcentriclowes.util.BaseFragment
import com.example.comcentriclowes.util.UIState
import com.example.comcentriclowes.util.convertKelvinToFahrenheit

class DetailsFragment : BaseFragment() {

    private val binding by lazy {
        FragmentDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        initViews(weatherViewModel.selectedForecast)
        return binding.root
    }

    private fun initViews(forecast: Forecast?) {
        forecast?.let {
            binding.tempDetails.text = forecast.main?.temp.convertKelvinToFahrenheit()
            binding.descWeather.text = forecast.weather?.firstOrNull()?.description ?: "No description"
            binding.tempFeels.text = String.format("Feels like: ${forecast.main?.feelsLike.convertKelvinToFahrenheit()}")
            binding.weatherTv.text = forecast.weather?.firstOrNull()?.main ?: "No available"
        } ?: let {
            binding.tempDetails.text = "-----"
            binding.descWeather.text = "No description"
            binding.tempFeels.text = "Feels like: ----"
            binding.weatherTv.text = "No available"
        }
    }
}