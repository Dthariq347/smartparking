package com.example.smartparking.Page

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.smartparking.R

// Data model for parking spots
data class Parking(
    val name: String,
    val info: String,
    val isAvailable: Boolean,
    val motorCount: Int,
    val mobilCount: Int
)

class ParkingAdapter(
    private val parkingList: List<Parking>
) : RecyclerView.Adapter<ParkingAdapter.ParkingViewHolder>() {

    private lateinit var listener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(clickListener: onItemClickListener) {
        listener = clickListener
    }

    class ParkingViewHolder(itemView: View, listener: onItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        val parkingName: TextView = itemView.findViewById(R.id.parkingName)
        val motorCountText: TextView = itemView.findViewById(R.id.parkingInfo)
        val mobilCountText: TextView = itemView.findViewById(R.id.mobilInfo)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_parking_adapter, parent, false)
        return ParkingViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ParkingViewHolder, position: Int) {
        val parking = parkingList[position]
        holder.parkingName.text = parking.name
        holder.motorCountText.text = "Motor: ${parking.motorCount}"
        holder.mobilCountText.text = "Mobil: ${parking.mobilCount}"

        // Logika untuk motorCount
        when {
            parking.motorCount < 300 -> {
                holder.motorCountText.setTextColor(Color.GREEN)
            }
            parking.motorCount in 300..350 -> {
                holder.motorCountText.setTextColor(Color.YELLOW)
            }
            parking.motorCount > 350 -> {
                holder.motorCountText.setTextColor(Color.RED)
            }
        }

        // Logika untuk mobilCount
        when {
            parking.mobilCount < 250 -> {
                holder.mobilCountText.setTextColor(Color.GREEN)
            }
            parking.mobilCount in 250..300 -> {
                holder.mobilCountText.setTextColor(Color.YELLOW)
            }
            parking.mobilCount > 300 -> {
                holder.mobilCountText.setTextColor(Color.RED)
            }
        }
    }

    override fun getItemCount() = parkingList.size
}
