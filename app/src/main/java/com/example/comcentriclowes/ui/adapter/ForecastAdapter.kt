package com.example.comcentriclowes.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.comcentriclowes.data.Forecast
import com.example.comcentriclowes.databinding.ForecastItemBinding
import com.example.comcentriclowes.util.convertKelvinToFahrenheit

class ForecastAdapter(
    private val forecastSet: MutableList<Forecast> = mutableListOf(),
    private val forecastClick: (Forecast) -> Unit
) : RecyclerView.Adapter<ForecastViewHolder>() {

    fun updateForecast(newData: List<Forecast>) {
        forecastSet.clear()
        forecastSet.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder =
        ForecastViewHolder(
            ForecastItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = forecastSet.size

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) =
        holder.bind(forecastSet[position], forecastClick)
}

class ForecastViewHolder(
    private val binding: ForecastItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(forecast: Forecast, forecastClick: (Forecast) -> Unit) {
        binding.forecastWeather.text = forecast.weather?.firstOrNull()?.main ?: " ---- "
        binding.tempTv.text = forecast.main?.temp.convertKelvinToFahrenheit()

        itemView.setOnClickListener { forecastClick(forecast) }
    }
}