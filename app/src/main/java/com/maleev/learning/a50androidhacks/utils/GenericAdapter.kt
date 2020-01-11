package com.maleev.learning.a50androidhacks.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView

class GenericAdapter<TItemType, TViewHolder : RecyclerView.ViewHolder>(
    @LayoutRes private val resourceId: Int,
    private val items: List<TItemType>,
    private val viewHolderCreator: (itemView: View) -> TViewHolder,
    private val onBindViewHolder: (TViewHolder, TItemType) -> Unit
) : RecyclerView.Adapter<TViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(resourceId, parent, false)
        return viewHolderCreator(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TViewHolder, position: Int) {
        onBindViewHolder(holder, items[position])
    }
}

inline fun <TItemType, reified TViewHolder : RecyclerView.ViewHolder> genericAdapter(
    @LayoutRes resourceId: Int,
    items: List<TItemType>,
    noinline onBindViewHolder: (TViewHolder, TItemType) -> Unit
): GenericAdapter<*, *> {
    return GenericAdapter(
        resourceId,
        items,
        { itemView -> TViewHolder::class.constructors.first().call(itemView) },
        onBindViewHolder
    )
}