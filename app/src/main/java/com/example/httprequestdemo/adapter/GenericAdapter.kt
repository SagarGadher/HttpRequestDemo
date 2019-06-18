package com.example.retrofitkotlin.adapert

import android.annotation.SuppressLint
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup

abstract class GenericAdapter<T> : ListAdapter<T, RecyclerView.ViewHolder>(object :
    DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = true

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem == newItem
}) {
    //complete
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        getBinding(parent, viewType)

    //complete
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(getItem(position))
        holder.itemView.setOnClickListener { onClick(getItem(position)) }
    }


    override fun getItemViewType(position: Int): Int = getLayoutId(position, getItem(position))


    protected abstract fun getLayoutId(position: Int, obj: T): Int

    abstract fun getBinding(view: View, viewType: Int): RecyclerView.ViewHolder

    abstract fun onClick(itemClicked: T)

    internal interface Binder<T> {
        fun bind(data: T)
    }
}