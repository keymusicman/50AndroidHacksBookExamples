package com.maleev.learning.a50androidhacks.utils

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter(
    private val items: List<Any>,
    private val binderHolders: List<BinderHolder<*, *>>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return binderHolders.indexOfFirst { it.canHandleType(items[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        binderHolders[viewType].createViewHolder(parent)

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        binderHolders.first { it.canHandleType(items[position]) }.binder.bindView(
            holder,
            items[position],
            this
        )
    }
}