package com.example.httprequestdemo.model

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class WeatherViewModelFactory(private val weather:Weather,private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java))
            return WeatherViewModel(weather,application) as T

        throw IllegalArgumentException("Unknown Model Class Found")
    }
}