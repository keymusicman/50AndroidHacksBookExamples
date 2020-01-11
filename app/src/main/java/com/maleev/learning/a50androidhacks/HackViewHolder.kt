package com.maleev.learning.a50androidhacks

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HackViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvHackNumber: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHackNumber) }
    val tvHackDescription: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHackDescription) }
}