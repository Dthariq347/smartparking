package com.example.smartparking.Page

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartparking.R

// Data model for parking spots
data class Parking(val name: String, val info: String, val isAvailable: Boolean)

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
    }

    override fun getItemCount() = parkingList.size
}
