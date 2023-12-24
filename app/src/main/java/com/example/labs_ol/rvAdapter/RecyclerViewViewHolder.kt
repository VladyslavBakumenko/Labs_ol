package com.example.labs_ol.rvAdapter

import androidx.recyclerview.widget.RecyclerView
import com.example.labs_ol.R
import com.example.labs_ol.Television
import com.example.labs_ol.databinding.RecyclerViewItemBinding

class RecyclerViewViewHolder(
    private val binding: RecyclerViewItemBinding,
    private val addProductClickListener: (entity: Television) -> Unit,
    private val removeProductClickListener: (cost: Int) -> Unit

) : RecyclerView.ViewHolder(binding.root) {

    private val context = binding.root.context

    private var selectedItems = 0

    fun onBind(entitie: Television) {
        with(binding) {
            root.setOnClickListener {
                addProductClickListener(entitie)
            }
            binding.tvEntityName.text = "Бренд = ${entitie.brand}"
            binding.tvEntityCost.text = "Розмір екрану = ${entitie.screenSize}"
            binding.tvProductQuantity.text = "Дата виготовлення = ${entitie.madeDate}"
            binding.tvEntityData.text = "Модель = ${entitie.model}"
        }
    }
}