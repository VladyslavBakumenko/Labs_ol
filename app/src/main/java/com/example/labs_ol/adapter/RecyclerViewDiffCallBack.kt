package com.example.labs_ol.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.labs_ol.ProductData

class RecyclerViewDiffCallBack : DiffUtil.ItemCallback<ProductData>() {
    override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return true
    }

    override fun areContentsTheSame(oldItem: ProductData, newItem: ProductData): Boolean {
        return true
    }
}