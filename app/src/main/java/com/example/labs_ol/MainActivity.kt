package com.example.labs_ol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labs_ol.adapter.RecyclerViewAdapter
import com.example.labs_ol.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var totalCost = 0
    private var selectedPlacesList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initRecyclerView()
        binding.startOtherActivity.setOnClickListener {
            startSecondActivity()
        }
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter { state, placeName ->
            if (state) addToList(placeName)
            else removeFromList(placeName)
            binding.tvPlaces.text = "Вибрані ноутбуки: ${selectedPlacesList}"
            if  (selectedPlacesList.isEmpty()) binding.tvPlaces.visibility = View.INVISIBLE
            else {
                binding.tvPlaces.visibility = View.VISIBLE
            }
        }

        with(binding.rvProducts) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recyclerViewAdapter
        }
        recyclerViewAdapter.submitList(createList())
    }

    private fun createList(): List<ProductData> {
        return listOf(
            ProductData("Dell", 1000, 4, R.drawable.first),
            ProductData("Samsung", 2000, 4, R.drawable.second),
            ProductData("Lenovo", 1500, 4, R.drawable.thirth),
        )
    }

    private fun addToList(placeName: String) {
        selectedPlacesList.add(placeName)
    }

    private fun removeFromList(placeName: String) {
        selectedPlacesList.remove(placeName)
    }

    private fun startSecondActivity() {
        if (selectedPlacesList.isNotEmpty()) {
            val id = when (selectedPlacesList.first()) {
                "Dell" -> R.drawable.first
                "Samsung" -> R.drawable.second
                "Lenovo" -> R.drawable.thirth
                else -> 4
            }
            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("test", id)
            }
            startActivity(intent)
        }
    }
}