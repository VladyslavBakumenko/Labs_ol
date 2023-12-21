package com.example.labs_ol

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TelevisionDao {
    @Insert
    suspend fun insert(television: Television)

    @Query("SELECT * FROM tv_table")
    suspend fun getAllTelevisions(): List<Television>
}