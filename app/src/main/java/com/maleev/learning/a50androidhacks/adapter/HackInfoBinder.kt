package com.maleev.learning.a50androidhacks.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.maleev.learning.a50androidhacks.HackInfo
import com.maleev.learning.a50androidhacks.utils.Binder
import com.maleev.learning.a50androidhacks.utils.hide
import com.maleev.learning.a50androidhacks.utils.setVisible
import com.maleev.learning.a50androidhacks.utils.show

class HackInfoBinder(val onClick: (HackInfo) -> Unit) : Binder<HackInfoVH, HackInfoVO>() {

    @SuppressLint("SetTextI18n")
    override fun bind(holder: HackInfoVH, data: HackInfoVO, adapter: RecyclerView.Adapter<*>) {

        holder.tvHackNumber.text = "Hack ${data.hackInfo.number}"
        holder.tvHackDescription.text = data.hackInfo.description

        holder.tvExpandBottomLine.hide()
        holder.layoutBottomLine.hide()
        holder.layoutBottomLine.removeAllViews()

        data.hackInfo.bottomLineProvider?.run {
            holder.tvExpandBottomLine.show()
            onCreateBottomLineView(holder.layoutBottomLine).run {
                holder.layoutBottomLine.addView(this)
            }

            holder.layoutBottomLine.setVisible(data.isExpanded)
        }

        holder.tvExpandBottomLine.setOnClickListener {
            data.isExpanded = !data.isExpanded
            adapter.notifyItemChanged(holder.adapterPosition)
        }

        holder.itemView.setOnClickListener {
            onClick(data.hackInfo)
        }
    }
}