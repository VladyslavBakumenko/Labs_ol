package com.example.labs_ol.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.labs_ol.ProductData
import com.example.labs_ol.databinding.RecyclerViewItemBinding

class RecyclerViewViewHolder(
    private val binding: RecyclerViewItemBinding,
    private val selectedListener: (state: Boolean, placeName: String) -> Unit

) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(productData: ProductData) {
        with(binding) {
            tvPlaceName.text = productData.product
            image.setImageResource(productData.image)
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                selectedListener(isChecked, productData.product)
            }
        }
    }
}