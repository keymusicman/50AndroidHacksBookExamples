package com.maleev.learning.a50androidhacks.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maleev.learning.a50androidhacks.HackInfo
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.utils.hide
import com.maleev.learning.a50androidhacks.utils.setVisible
import com.maleev.learning.a50androidhacks.utils.show

class HackListAdapter(
    private val hacks: List<HackInfoVO>,
    private val onClickHack: (HackInfo) -> Unit
) :
    RecyclerView.Adapter<HackListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackListViewHolder {
        return LayoutInflater.from(parent.context).inflate(R.layout.layout_hack_item, parent, false)
            .let {
                HackListViewHolder(it)
                    .apply {
                        tvExpandBottomLine.setOnClickListener {
                            val hack = hacks[adapterPosition]
                            hack.isExpanded = !hack.isExpanded
                            notifyItemChanged(adapterPosition)
                        }

                        itemView.setOnClickListener {
                            val hack = hacks[adapterPosition]
                            onClickHack(hack.hackInfo)
                        }
                    }
            }
    }

    override fun getItemCount(): Int = hacks.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HackListViewHolder, position: Int) {
        val hack = hacks[position]

        holder.tvHackNumber.text = "Hack ${hack.hackInfo.number}"
        holder.tvHackDescription.text = hack.hackInfo.description

        holder.tvExpandBottomLine.hide()
        holder.layoutBottomLine.hide()
        holder.layoutBottomLine.removeAllViews()

        hack.hackInfo.bottomLineProvider?.run {
            holder.tvExpandBottomLine.show()
            onCreateBottomLineView(holder.layoutBottomLine).run {
                holder.layoutBottomLine.addView(this)
            }

            holder.layoutBottomLine.setVisible(hack.isExpanded)
        }

    }
}

class HackListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val tvHackNumber: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHackNumber) }
    val tvHackDescription: TextView by lazy { itemView.findViewById<TextView>(R.id.tvHackDescription) }
    val tvExpandBottomLine: TextView by lazy { itemView.findViewById<TextView>(R.id.tvExpandBottomLine) }
    val layoutBottomLine: ViewGroup by lazy { itemView.findViewById<ViewGroup>(R.id.layoutBottomLine) }
}