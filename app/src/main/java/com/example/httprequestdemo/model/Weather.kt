package com.example.httprequestdemo.model

import android.arch.lifecycle.ViewModel

data class Weather(
        var city:String="1",
        var country:String="2",
        var sunriseTime :Long=0L,
        var sunsetTime:Long=0L,
        var status:String="3",
        var description:String="4",
        var search :String="Surat"
)