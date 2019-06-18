package com.example.httprequestdemo.activity

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.httprequestdemo.R
import com.example.httprequestdemo.databinding.LayoutActivityMainBinding
import com.example.httprequestdemo.model.Weather
import com.example.httprequestdemo.model.WeatherViewModel
import com.example.httprequestdemo.model.WeatherViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var v: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: LayoutActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.layout_activity_main)
        val weather = Weather()
        val viewmodelFactory = WeatherViewModelFactory(weather,application)
        v = ViewModelProviders.of(this, viewmodelFactory).get(WeatherViewModel::class.java)
        binding.vm = v
        binding.lifecycleOwner = this

    }
}
