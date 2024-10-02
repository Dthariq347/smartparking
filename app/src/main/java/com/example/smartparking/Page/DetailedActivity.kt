package com.example.smartparking.Page

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.smartparking.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetailedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_selectedparking)

        //Selected Parking
        val selectedParkingName: TextView = findViewById(R.id.selectedparkingName)
        val selectedParkingInfo: TextView = findViewById(R.id.selectedparkingInfo)

        val bundle : Bundle? = intent.extras
        val test = bundle!!.getString("test")
        val parkingName = bundle.getString("name")
        val parkingInfo = bundle.getString("info")

        selectedParkingName.text = parkingName
        selectedParkingInfo.text = parkingInfo


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Setup RecyclerView for displaying parking spots
        val selectedRecyclerView: RecyclerView = findViewById(R.id.selectedRecyclerView)
        selectedRecyclerView.layoutManager = LinearLayoutManager(this)

        // Example data for parking spots
        val parkingSpots = listOf(
            Parking(name = "Binus Alam Sutera", isAvailable = true, motorCount = 362, mobilCount = 251, info = "Informasi tambahan tentang Binus Alam Sutera"),
            Parking(name = "Pacific Garden Alam Sutera", isAvailable = true, motorCount = 320, mobilCount = 80, info = "Parkir aman dan luas di Pacific Garden"),
            Parking(name = "Mall Alam Sutera", isAvailable = true, motorCount = 45, mobilCount = 60, info = "Parkir hampir penuh di Mall Alam Sutera"),
            Parking(name = "Ruko Woodlake", isAvailable = false, motorCount = 30, mobilCount = 40, info = "Parkiran tidak tersedia di Ruko Woodlake"),
            Parking(name = "Binus Kemanggisan", isAvailable = false, motorCount = 37, mobilCount = 20, info = "Informasi tentang parkiran Binus Kemanggisan")
        )


        val adapter = ParkingAdapter(parkingSpots)
        selectedRecyclerView.adapter = adapter
        selectedRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : ParkingAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                val intent = Intent(this@DetailedActivity, DetailedActivity::class.java)

                intent.putExtra("name", parkingSpots[position].name)
                intent.putExtra("info", parkingSpots[position].info)
                startActivity(intent)

            }

        })
        //Bottom navbar
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.selectedItemId = R.id.navigation_home

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    //pindah ke Home
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
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