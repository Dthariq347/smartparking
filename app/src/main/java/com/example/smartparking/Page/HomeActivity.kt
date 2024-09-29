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
            Parking("Binus Alam Sutera", "Motor: 362 | Mobil: 20", true),
            Parking("Pacific Garden Alam Sutera", "Motor: 60 | Mobil: 80", true),
            Parking("Mall Alam Sutera", "Motor: 45 | Mobil: 60", true),
            Parking("Ruko Woodlake", "Motor: 30 | Mobil: 40", false),
            Parking("Binus Kemanggisan", "Motor: 37 | Mobil: 20", false)
        )

        // Set the adapter for RecyclerView
        recyclerView.adapter = ParkingAdapter(parkingSpots)

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
                    // Tambahkan logika jika ada halaman notifikasi
                    true
                }
                else -> false
            }
            }

    }
}
