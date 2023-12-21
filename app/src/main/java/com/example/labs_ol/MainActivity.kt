package com.example.labs_ol

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labs_ol.adapter.RecyclerViewAdapter
import com.example.labs_ol.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private var totalCost = 0
    private var selectedProductsList = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        initRecyclerView()
        binding.btnList.setOnClickListener {
            startSecondActivity()
        }
    }

    private fun initRecyclerView() {
        recyclerViewAdapter = RecyclerViewAdapter({
            totalCost += it
            binding.tvTotalCost.text = "Загальна ціна = $totalCost"
            addToList(it)
        }, {
            totalCost -= it
            binding.tvTotalCost.text = "Загальна ціна = $totalCost"
            removeFromList(it)
        })

        with(binding.rvProducts) {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = recyclerViewAdapter
        }
        recyclerViewAdapter.submitList(createList())
    }

    private fun createList(): List<ProductData> {
        return listOf(
            ProductData("Dell", 1000, 4),
            ProductData("Samsung", 2000, 4),
            ProductData("Lenovo", 1500, 4),
            ProductData("Apple", 4000, 4),
            ProductData("HP", 800, 4)
        )
    }

    private fun removeFromList(cost: Int) {
        when (cost) {
            1000 -> selectedProductsList.remove("Dell")
            2000 -> selectedProductsList.remove("Samsung")
            1500 -> selectedProductsList.remove("Lenovo")
            4000 -> selectedProductsList.remove("Apple")
            800 -> selectedProductsList.remove("HP")
        }
    }

    private fun addToList(cost: Int) {
        when (cost) {
            1000 -> selectedProductsList.add("Dell")
            2000 -> selectedProductsList.add("Samsung")
            1500 -> selectedProductsList.add("Lenovo")
            4000 -> selectedProductsList.add("Apple")
            800 -> selectedProductsList.add("HP")
        }
    }

    private fun startSecondActivity() {
        val intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("test", selectedProductsList.toString())
        }
        startActivity(intent)
    }
}