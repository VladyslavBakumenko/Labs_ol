package com.example.labs_ol

import android.app.Activity
import android.os.Bundle
import androidx.room.Room
import com.example.labs_ol.databinding.AddTvActivityLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTVActivity : Activity() {

    private lateinit var binding: AddTvActivityLayoutBinding
    private val scope = CoroutineScope(Dispatchers.IO)
    private var database: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddTvActivityLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setOnClickListeners()
    }

    private fun setOnClickListeners() {
        binding.addTV.setOnClickListener {
            val brand = binding.etBrand.text.toString()
            val madeDate = binding.etMadeDate.text.toString()
            val model = binding.etModel.text.toString()
            val screenSize = binding.etScreenSize.text.toString().toInt()
            val tv = Television(
                brand = brand,
                model = model,
                screenSize = screenSize,
                madeDate = madeDate
            )
            scope.launch {
                if (database == null) {
                    database = Room.databaseBuilder(
                        applicationContext,
                        AppDatabase::class.java, "app_database"
                    ).fallbackToDestructiveMigration().build()
                }
                database?.televisionDao()?.insert(tv)
            }
        }
    }
}