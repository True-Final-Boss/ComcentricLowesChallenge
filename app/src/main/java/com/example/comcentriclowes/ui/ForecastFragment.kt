package com.example.comcentriclowes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.comcentriclowes.R
import com.example.comcentriclowes.databinding.FragmentForecastBinding
import com.example.comcentriclowes.ui.adapter.ForecastAdapter
import com.example.comcentriclowes.util.BaseFragment
import com.example.comcentriclowes.util.UIState

class ForecastFragment : BaseFragment() {

    private val binding by lazy {
        FragmentForecastBinding.inflate(layoutInflater)
    }

    private val forecastAdapter by lazy {
        ForecastAdapter {
            weatherViewModel.selectedForecast = it
            findNavController().navigate(R.id.action_ForecastFragment_to_DetailsFragment,
                bundleOf(Pair("labelCity", weatherViewModel.searchCity?.uppercase())))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.forecastRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = forecastAdapter
        }

        weatherViewModel.forecast.observe(viewLifecycleOwner) {
            when(it) {
                is UIState.LOADING -> {
                    binding.forecastRv.visibility = View.GONE
                    binding.loadIcon.visibility = View.VISIBLE
                }
                is UIState.SUCCESS -> {
                    binding.forecastRv.visibility = View.VISIBLE
                    binding.loadIcon.visibility = View.GONE
                    forecastAdapter.updateForecast(it.response)
                }
                is UIState.ERROR -> {
                    binding.forecastRv.visibility = View.GONE
                    binding.loadIcon.visibility = View.GONE
                    showError(it.error.localizedMessage) {
                        weatherViewModel.getCityForecast()
                    }
                }
            }
        }

        weatherViewModel.getCityForecast()

        return binding.root
    }
}