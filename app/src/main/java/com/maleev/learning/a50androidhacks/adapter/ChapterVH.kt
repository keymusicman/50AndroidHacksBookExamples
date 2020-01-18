package com.maleev.learning.a50androidhacks.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.utils.annotations.LayoutId

@LayoutId(R.layout.layout_chapter_item)
class ChapterVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvTitle: TextView by lazy { itemView.findViewById<TextView>(R.id.tvTitle) }
    val tvNumber: TextView by lazy { itemView.findViewById<TextView>(R.id.tvNumber) }
}