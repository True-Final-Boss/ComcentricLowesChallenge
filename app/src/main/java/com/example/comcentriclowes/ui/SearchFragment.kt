package com.example.comcentriclowes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.comcentriclowes.R
import com.example.comcentriclowes.databinding.FragmentSearchBinding
import com.example.comcentriclowes.util.BaseFragment

class SearchFragment : BaseFragment() {

    private val binding by lazy {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding.lookBtn.setOnClickListener {
            val city = binding.cityEt.text.toString()
            if (city.isNotBlank()) {
                weatherViewModel.searchCity = city
                findNavController().navigate(R.id.action_SearchFragment_to_ForecastFragment,
                bundleOf(Pair("labelCity", city.uppercase()))
                )
            }
        }

        return binding.root
    }
}