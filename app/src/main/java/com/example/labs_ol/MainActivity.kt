package com.example.labs_ol

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.labs_ol.databinding.ActivityMainBinding
import com.example.labs_ol.rvAdapter.RecyclerViewAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var database: AppDatabase? = null
    private val scope = CoroutineScope(Dispatchers.IO)
    private var recyclerViewAdapter: RecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.addNewTvButton.setOnClickListener {
            val myIntent = Intent(this, AddTVActivity::class.java)
            this@MainActivity.startActivity(myIntent)
        }
        getAllEntities()
    }

    override fun onResume() {
        super.onResume()
        getAllEntities()
    }

    private fun getAllEntities() {
        scope.launch {
            if (database == null) {
                database = Room.databaseBuilder(
                    applicationContext,
                    AppDatabase::class.java, "app_database"
                ).fallbackToDestructiveMigration().build()
            }
            if (recyclerViewAdapter == null) {
                initRecyclerView(database?.televisionDao()?.getAllTelevisions()!!)
            } else {
                recyclerViewAdapter?.submitList(database?.televisionDao()?.getAllTelevisions()!!)
            }
        }
    }

    private fun initRecyclerView(allTelevisions: List<Television>) {
        scope.launch(Dispatchers.Main) {
            recyclerViewAdapter = RecyclerViewAdapter({
                val myIntent = Intent(this@MainActivity, AddTVActivity::class.java)
                EntityArg.getInstance(it)
                this@MainActivity.startActivity(myIntent)
            }, {

            })

            with(binding.recyclerView) {
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = recyclerViewAdapter
            }
            recyclerViewAdapter?.submitList(allTelevisions)
            recyclerViewAdapter?.notifyDataSetChanged()
        }
    }
}