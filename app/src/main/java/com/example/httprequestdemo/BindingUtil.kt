package com.example.httprequestdemo

import android.databinding.BindingAdapter
import android.widget.TextView
import com.example.httprequestdemo.model.Weather


@BindingAdapter("setCountry")
fun TextView.setCountry(item: Weather) {
    item.let {
        text = "Country: ${item.country}"
    }
}

@BindingAdapter("setCity")
fun TextView.setCity(item: Weather) {
    item.let {
        text = "City: ${item.city}"
    }
}

@BindingAdapter("setStatus")
fun TextView.setStatus(item: Weather) {
    item.let {
        text = "environment: ${item.status}"
    }
}

@BindingAdapter("setDesc")
fun TextView.setDescription(item: Weather) {
    item.let {
        text = "Description: ${item.description}"
    }
}

@BindingAdapter("setSunrise")
fun TextView.setSunRise(item: Weather) {
    item?.let {
        text = "Sun Rise at: ${convertLongToTime(item.sunriseTime)}"
    }
}

@BindingAdapter("setSunset")
fun TextView.setSunSet(item: Weather) {
    item?.let {
        text = "Sun Set at: ${convertLongToTime(item.sunsetTime)}"
    }
}

fun convertLongToTime(seconds: Long): String {
    val s = seconds % 60
    val m = seconds / 60 % 60
    val h = seconds / (60 * 60) % 24
    return String.format("%d:%02d:%02d", h, m, s)

}