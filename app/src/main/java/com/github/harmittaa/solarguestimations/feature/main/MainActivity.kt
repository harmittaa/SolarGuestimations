package com.github.harmittaa.solarguestimations.feature.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.harmittaa.solarguestimations.R
import com.github.harmittaa.solarguestimations.databinding.ActivityMainBinding
import com.github.harmittaa.solarguestimations.feature.forecast.ForecastFragment
import com.github.harmittaa.solarguestimations.persistence.ApiKeyPreferences
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module


class MainActivity : AppCompatActivity() {
    private lateinit var mainActivityViewModel: MainActivityViewModel

    private val stateObserver = Observer<LandingState> {
        when (it!!) {
            LandingState.ERROR -> showError()
            LandingState.SUCCESS -> showForecast()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.landingState.observe(this, stateObserver)
        binding.viewmodel = mainActivityViewModel
        mainActivityViewModel.onCreate(ApiKeyPreferences(this))
    }

    private fun showError() {
        AlertDialog.Builder(this)
            .setTitle("API key error")
            .setMessage("Now you can only exit the app")
            .setNeutralButton("Finish") { _, _ ->  finish()}
    }

    private fun showForecast() {
        supportFragmentManager.beginTransaction().add(R.id.root, ForecastFragment(), "forecast").commit()
    }
}
