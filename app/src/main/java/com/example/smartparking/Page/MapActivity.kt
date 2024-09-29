package com.example.smartparking.Page

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.maps.MapView
import com.example.smartparking.R
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.locationcomponent.location
import android.content.Intent
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.plugin.animation.camera
import com.mapbox.geojson.Point



class MapActivity : AppCompatActivity() {
    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        // Inisialisasi MapView
        mapView = findViewById(R.id.mapView)
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS)

        // Setup Peta dengan Mapbox
        mapView.getMapboxMap().loadStyleUri(Style.MAPBOX_STREETS) {
            // Setelah style berhasil dimuat, kita set posisi kamera
            val cameraOptions = CameraOptions.Builder()
                .center(Point.fromLngLat(106.649346, -6.224497)) // Koordinat Indonesia
                .zoom(16.0) // Zoom level, 4 adalah untuk menampilkan seluruh Indonesia
                .build()

            // Fly ke posisi yang diinginkan
            mapView.camera.flyTo(cameraOptions)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // Set Map as the selected item
        bottomNavigationView.selectedItemId = R.id.navigation_map

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Pindah ke MainActivity (Home)
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.navigation_map -> {
                    // Tetap di halaman Map, tidak perlu tindakan
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

    private fun enableUserLocation() {
        // Menampilkan lokasi pengguna jika izin diberikan
        mapView.location.updateSettings {
            enabled = true
            pulsingEnabled = true
        }
    }



}
