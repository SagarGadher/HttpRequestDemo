package com.example.retrofitkotlin.adapert

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import com.example.httprequestdemo.R
import com.example.httprequestdemo.model.Weather

object GenericViewHolderFactory {
    fun create(binding: ViewDataBinding, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            //R.layout.list_weather_item -> WeatherViewHolder(binding)
            else -> WeatherViewHolder(binding)
        }
    }

    //without data binding generic view Holder
    /*class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        GenericAdapter.Binder<Shlok> {
        var tvListItem: TextView
        var tvListItemCount: TextView

        init {
            tvListItemCount = itemView.findViewById(R.id.tvListItemCount)
            tvListItem = itemView.findViewById(R.id.tvListItem)
        }

        override fun bind(data: Shlok) {
            tvListItem.text = data.shlok
            tvListItemCount.text = data.index.toString()
        }
    }*/

    // with data binding View Holder
    class WeatherViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root), GenericAdapter.Binder<Weather> {
        override fun bind(data: Weather) {
            //binding as ListHomeItemBinding
            //binding.property = data
            binding.executePendingBindings()
        }
    }
}