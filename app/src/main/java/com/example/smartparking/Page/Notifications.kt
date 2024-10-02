package com.example.smartparking.Page

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color
import android.widget.TextView
import com.example.smartparking.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class Notifications : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_notifications)

        // Mengatur window insets untuk handling padding di sistem bar
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.selectedItemId = R.id.navigation_notifications

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_map -> {
                    val intent = Intent(this, MapActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_notifications -> {
                    // Tetap di halaman notifikasi
                    true
                }
                else -> false
            }
        }

        // Update warna indikator ketersediaan parkiran
        updateParkingIndicators()
    }

    // Fungsi untuk memperbarui indikator warna berdasarkan jumlah motor/parkir
    private fun updateParkingIndicators() {
        val motorCount1 = 262 // Contoh data Binus Alam Sutera
        val motorCount2 = 20  // Contoh data Pacific Garden
        val motorCount3 = 362 // Contoh data Binus Kemanggisan

        // Logika Binus Alam Sutera (Motor)
        when {
            motorCount1 < 250 -> findViewById<TextView>(R.id.motorCountText1).setTextColor(Color.GREEN)
            motorCount1 in 250..299 -> findViewById<TextView>(R.id.motorCountText1).setTextColor(Color.YELLOW) // Warna kuning untuk "Hampir Penuh"
            motorCount1 >= 300 -> findViewById<TextView>(R.id.motorCountText1).setTextColor(Color.RED)
        }

        // Logika Pacific Garden (Mobil)
        when {
            motorCount2 < 250 -> findViewById<TextView>(R.id.motorCountText2).setTextColor(Color.GREEN)
            motorCount2 in 250..299 -> findViewById<TextView>(R.id.motorCountText2).setTextColor(Color.YELLOW) // Warna kuning untuk "Hampir Penuh"
            motorCount2 >= 300 -> findViewById<TextView>(R.id.motorCountText2).setTextColor(Color.RED)
        }

        // Logika Binus Kemanggisan (Motor)
        when {
            motorCount3 < 250 -> findViewById<TextView>(R.id.motorCountText3).setTextColor(Color.GREEN)
            motorCount3 in 250..299 -> findViewById<TextView>(R.id.motorCountText3).setTextColor(Color.YELLOW) // Warna kuning untuk "Hampir Penuh"
            motorCount3 >= 300 -> findViewById<TextView>(R.id.motorCountText3).setTextColor(Color.RED)
        }
    }
}
