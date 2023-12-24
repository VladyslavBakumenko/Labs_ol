package com.example.labs_ol.rvAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.labs_ol.Television
import com.example.labs_ol.databinding.RecyclerViewItemBinding

class RecyclerViewAdapter(
    private val addProductClickListener: (entity: Television) -> Unit,
    private val removeProductClickListener: (cost: Int) -> Unit
) :
    ListAdapter<Television, RecyclerViewViewHolder>(RecyclerViewDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewViewHolder {
        val binding = RecyclerViewItemBinding.inflate(LayoutInflater.from(parent.context))
        return RecyclerViewViewHolder(binding, addProductClickListener, removeProductClickListener)
    }

    override fun onBindViewHolder(holder: RecyclerViewViewHolder, position: Int) {
        currentList[position].let {
            holder.onBind(it)
        }
    }
}