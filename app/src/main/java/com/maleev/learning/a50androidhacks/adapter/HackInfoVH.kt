package com.maleev.learning.a50androidhacks.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.utils.annotations.LayoutId

@LayoutId(R.layout.layout_hack_item)
class HackInfoVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvHackNumber: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHackNumber) }
    val tvHackDescription: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHackDescription) }
    val tvExpandBottomLine: TextView by lazy { itemView.findViewById<TextView>(R.id.tvExpandBottomLine) }
    val layoutBottomLine: ViewGroup by lazy { itemView.findViewById<ViewGroup>(R.id.layoutBottomLine) }
}