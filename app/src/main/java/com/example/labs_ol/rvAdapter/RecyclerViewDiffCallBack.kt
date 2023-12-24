package com.example.labs_ol.rvAdapter

import androidx.recyclerview.widget.DiffUtil
import com.example.labs_ol.Television

class RecyclerViewDiffCallBack : DiffUtil.ItemCallback<Television>() {
    override fun areItemsTheSame(oldItem: Television, newItem: Television): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Television, newItem: Television): Boolean {
        return oldItem.id == newItem.id
    }
}