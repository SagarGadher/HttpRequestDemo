package com.example.httprequestdemo.model

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class WeatherViewModel(val weather1: Weather, val application: Application) : ViewModel() {
    private val viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.IO)
    private val _weather = MutableLiveData<Weather>()

    val weather: LiveData<Weather>
        get() = _weather

    init {
        _weather.value = weather1
        getDataFromUrl("Surat")
    }

    fun getDataFromUrl(search:String) {
        _weather.value!!.search =search
        getData()
    }

    private fun getData() {
        coroutineScope.launch {
            Log.e("APICALL", "Happen")
            val urlConnect = connectToApi()

            when (urlConnect.responseCode) {
                HttpURLConnection.HTTP_OK -> {
                    val content = convertStreamToString(urlConnect)
                    Log.e("JSONSTRING",content)
                    val json = JSONObject(content)
                    val jo1: JSONObject = json.getJSONObject("sys")
                    val ja1: JSONArray = json.getJSONArray("weather")
                    val jo2: JSONObject = ja1.getJSONObject(0)
                    val city = json.getString("name")
                    val country = jo1.getString("country")
                    val sunriseTime = jo1.getLong("sunrise")
                    val sunsetTime = jo1.getLong("sunset")
                    val status = jo2.getString("main")
                    val description = jo2.getString("description")

                    withContext(Dispatchers.Main){
                        val weather = Weather(city, country, sunriseTime, sunsetTime, status, description)
                        _weather.value = weather
                    }
                }
                HttpURLConnection.HTTP_NOT_FOUND -> {
                    Toast.makeText(application, "City Not Found !", Toast.LENGTH_LONG).show()
                }
                HttpURLConnection.HTTP_CLIENT_TIMEOUT -> {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            application,
                            "Connection Error Please Check Internet And Try Again !",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private suspend fun connectToApi(): HttpURLConnection {
        val url = URL("https://api.openweathermap.org/data/2.5/weather?q=${_weather.value?.search}&appid=2137da2426b35047ed50f621b63dcade")
        /*val urlConnect = url.openConnection() as HttpURLConnection*/
        val urlConnect = url.openConnection() as HttpURLConnection
        Log.e("APICALL", "Happen1")
        return urlConnect
    }

    private suspend fun convertStreamToString(urlConnect:HttpURLConnection):String{
        val inputStreamData = urlConnect.inputStream
        val inputString =inputStreamData.bufferedReader().use(BufferedReader::readText)
        Log.e("APICALL", "Happen2")
        return inputString
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}