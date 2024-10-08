package com.example.smartparking.Page

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartparking.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.content.Intent

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Setup RecyclerView for displaying parking spots
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewParking)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Example data for parking spots
        val parkingSpots = listOf(
            Parking(name = "Binus Alam Sutera", isAvailable = true, motorCount = 362, mobilCount = 251, info = "Informasi tambahan tentang Binus Alam Sutera"),
            Parking(name = "Pacific Garden Alam Sutera", isAvailable = true, motorCount = 320, mobilCount = 80, info = "Parkir aman dan luas di Pacific Garden"),
            Parking(name = "Mall Alam Sutera", isAvailable = true, motorCount = 45, mobilCount = 60, info = "Parkir hampir penuh di Mall Alam Sutera"),
            Parking(name = "Ruko Woodlake", isAvailable = false, motorCount = 30, mobilCount = 40, info = "Parkiran tidak tersedia di Ruko Woodlake"),
            Parking(name = "Binus Kemanggisan", isAvailable = false, motorCount = 37, mobilCount = 20, info = "Informasi tentang parkiran Binus Kemanggisan")
        )

        val adapter = ParkingAdapter(parkingSpots)

        // Set the adapter for RecyclerView
        recyclerView.adapter = adapter

        // Mengatur klik listener ketika item di RecyclerView diklik
        adapter.setOnItemClickListener(object : ParkingAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@HomeActivity, DetailedActivity::class.java)
                // Mengirim data 'name' dan 'info' ke DetailedActivity
                intent.putExtra("name", parkingSpots[position].name)
                intent.putExtra("info", parkingSpots[position].info)
                startActivity(intent)
            }
        })

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        // Set the default selected item
        bottomNavigationView.selectedItemId = R.id.navigation_home

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Tetap di Home, tidak perlu tindakan
                    true
                }
                R.id.navigation_map -> {
                    // Pindah ke MapActivity
                    val intent = Intent(this, MapActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_notifications -> {
                    val intent = Intent(this, Notifications::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
