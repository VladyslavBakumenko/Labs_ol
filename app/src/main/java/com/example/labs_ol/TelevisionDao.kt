package com.example.labs_ol

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TelevisionDao {
    @Insert
    suspend fun insert(television: Television)

    @Query("SELECT * FROM tv_table")
    suspend fun getAllTelevisions(): List<Television>

    @Update
    suspend fun update(television: Television)

    @Delete
    suspend fun delete(television: Television)
}