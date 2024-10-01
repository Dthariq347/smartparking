package com.example.smartparking.Page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartparking.R
import android.graphics.Color

// Data model for parking spots
data class Parking(val name: String, val info: String, val isAvailable: Boolean, val motorCount: Int)


class ParkingAdapter(private val parkingList: List<Parking>) : RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder>() {

    class ParkingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val parkingName: TextView = itemView.findViewById(R.id.parkingName)
        val parkingInfo: TextView = itemView.findViewById(R.id.parkingInfo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_parking_adapter, parent, false)
        return ParkingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        val parking = parkingList[position]
        holder.parkingName.text = parking.name
        holder.parkingInfo.text = parking.info

        // Ambil jumlah motor dari teks (misal "Motor: 362 | Mobil: 20")
        val motorCount = parking.info.substringAfter("Motor: ").substringBefore(" | Mobil:").toInt()

        // Logic untuk mengganti warna berdasarkan jumlah motor
        when {
            motorCount < 300 -> {
                holder.parkingInfo.setTextColor(Color.GREEN) // Teks hijau untuk "Available"
            }
            motorCount in 300..350 -> {
                holder.parkingInfo.setTextColor(Color.YELLOW) // Teks kuning untuk "Almost Full"
            }
            motorCount > 350 -> {
                holder.parkingInfo.setTextColor(Color.RED) // Teks merah untuk "High Risk"
            }
        }
    }


    override fun getItemCount() = parkingList.size


}
