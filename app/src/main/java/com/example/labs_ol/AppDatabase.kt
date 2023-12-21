package com.example.labs_ol

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Television::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun televisionDao(): TelevisionDao
}