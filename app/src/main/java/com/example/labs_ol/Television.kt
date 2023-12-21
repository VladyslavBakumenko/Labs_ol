package com.example.labs_ol

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tv_table")
data class Television(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val brand: String,
    val model: String,
    val screenSize: Int,
    val madeDate: String
)