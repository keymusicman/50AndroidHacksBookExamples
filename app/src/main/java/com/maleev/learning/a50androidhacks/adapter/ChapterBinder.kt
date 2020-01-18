package com.maleev.learning.a50androidhacks.adapter

import androidx.recyclerview.widget.RecyclerView
import com.maleev.learning.a50androidhacks.utils.Binder

class ChapterBinder : Binder<ChapterVH, ChapterVO>() {
    override fun bind(holder: ChapterVH, data: ChapterVO, adapter: RecyclerView.Adapter<*>) {
        holder.tvTitle.text = data.chapter.title
        holder.tvNumber.text = "${data.chapter.number}"
    }
}