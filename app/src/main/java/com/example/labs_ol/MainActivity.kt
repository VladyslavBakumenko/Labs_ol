package com.example.labs_ol

import android.R.attr.value
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.labs_ol.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var database: AppDatabase? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        test()
        binding.addNewTvButton.setOnClickListener {
            val myIntent = Intent(this, AddTVActivity::class.java)
            this@MainActivity.startActivity(myIntent)
        }
    }

    private fun test() {
        scope.launch {
            if (database == null) {
                database = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "app_database"
                ).fallbackToDestructiveMigration().build()
            }
            Log.d("fdsgdfsgdfsgfs", database?.televisionDao()?.getAllTelevisions().toString())
        }
    }
}