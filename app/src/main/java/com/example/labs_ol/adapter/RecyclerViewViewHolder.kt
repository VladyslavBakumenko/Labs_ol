package com.example.labs_ol.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.labs_ol.ProductData
import com.example.labs_ol.databinding.RecyclerViewItemBinding

class RecyclerViewViewHolder(
    private val binding: RecyclerViewItemBinding,
    private val addProductClickListener: (cost: Int) -> Unit,
    private val removeProductClickListener: (cost: Int) -> Unit

) : RecyclerView.ViewHolder(binding.root) {

    private var selectedItems = 0

    fun onBind(productData: ProductData) {
        with(binding) {
            tvProductName.text = productData.product
            tvProductCost.text = "Cost = ${productData.cost}"
            ivMinus.setOnClickListener {
                if (selectedItems != 0) {
                    selectedItems--
                    tvProductQuantity.text = "Selected items count = $selectedItems"
                    removeProductClickListener(productData.cost)
                }
            }
            ivPlus.setOnClickListener {
                selectedItems++
                tvProductQuantity.text = "Selected items count = $selectedItems"
                addProductClickListener(productData.cost)
            }
        }
    }
}