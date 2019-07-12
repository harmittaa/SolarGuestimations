package com.github.harmittaa.solarguestimations.feature.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.harmittaa.solarguestimations.R
import com.github.harmittaa.solarguestimations.model.Forecast
import timber.log.Timber

class ForecastFragment : Fragment() {
    private val forecastViewModel by viewModels<ForecastViewModel>()
    private val observer = Observer<Forecast> {
        Timber.d("Viewmodel: observer")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.forecast, container, false)
    }

    override fun onResume() {
        super.onResume()
        forecastViewModel.forecast.observe(this, observer)
    }
}