package com.github.harmittaa.solarguestimations.feature.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.github.harmittaa.solarguestimations.R
import com.github.harmittaa.solarguestimations.model.Forecast
import com.github.harmittaa.solarguestimations.repository.ForecastRepository
import okhttp3.internal.Internal.instance
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module
import timber.log.Timber

val vmModule = module {
    viewModel { ForecastViewModel() }
}

class ForecastFragment : Fragment() {
    //private val forecastViewModel by viewModels<ForecastViewModel>()
    val forecastViewModel: ForecastViewModel by viewModel()

    private val observer = Observer<Forecast> {
        Timber.d("Response received")
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