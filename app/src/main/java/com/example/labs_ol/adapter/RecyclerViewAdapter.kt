package com.example.labs_ol.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.labs_ol.ProductData
import com.example.labs_ol.databinding.RecyclerViewItemBinding

class RecyclerViewAdapter(
    private val selectedListener: (state: Boolean, placeName: String) -> Unit

) :
    ListAdapter<ProductData, RecyclerViewViewHolder>(RecyclerViewDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context))
        return RecyclerViewViewHolder(binding, selectedListener)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        currentList[position].let {
            holder.onBind(it)
        }
    }
}