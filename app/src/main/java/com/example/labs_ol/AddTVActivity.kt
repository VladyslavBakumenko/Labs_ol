package com.example.labs_ol

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.room.Room
import com.example.labs_ol.databinding.AddTvActivityLayoutBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddTVActivity : Activity() {

    private lateinit var binding: AddTvActivityLayoutBinding
    private val scope = CoroutineScope(Dispatchers.IO)
    private var database: AppDatabase? = null

    private val editedEntity: Television? = EntityArg.justGetInstance()?.entity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddTvActivityLayoutBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setOnClickListeners()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("entity", Television::class.java)
        }
    }

    private fun setOnClickListeners() {
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "app_database"
        ).fallbackToDestructiveMigration().build()

        if (editedEntity != null) {
            binding.addTV.text = "Змінити"
            binding.etBrand.setText(editedEntity.brand)
            binding.etModel.setText(editedEntity.model)
            binding.etScreenSize.setText(editedEntity.screenSize.toString())
            binding.etMadeDate.setText(editedEntity.madeDate)
            binding.deleteEntity.visibility = View.VISIBLE
            binding.deleteEntity.setOnClickListener {
                scope.launch {
                    database?.televisionDao()?.delete(editedEntity)
                }
                Toast.makeText(this, "Телевізор видалено з бази данних", Toast.LENGTH_SHORT).show()
                this.onBackPressed()
            }
        }
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
                if (editedEntity == null) {
                    database?.televisionDao()?.insert(tv)
                } else {
                    database?.televisionDao()?.update(
                        editedEntity.copy(
                            brand = brand,
                            model = model,
                            screenSize = screenSize,
                            madeDate = madeDate
                        )
                    )
                }
            }
            if (editedEntity == null) {
                Toast.makeText(this, "Телевізор додано до бази данних", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Телевізор змінено", Toast.LENGTH_SHORT).show()
            }
            this.onBackPressed()
        }
    }
}