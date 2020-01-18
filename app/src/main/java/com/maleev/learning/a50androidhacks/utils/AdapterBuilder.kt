package com.maleev.learning.a50androidhacks.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maleev.learning.a50androidhacks.utils.annotations.LayoutId

class AdapterBuilder(private val items: List<Any>) {
    val binderHolders = mutableListOf<BinderHolder<*, *>>()

    inline fun <reified VH : RecyclerView.ViewHolder, reified VO> withItem(
        binder: Binder<VH, VO>
    ): AdapterBuilder {
        binderHolders.add(
            BinderHolder(
                binder,
                VH::class.java.getLayoutId(),
                { vo -> vo is VO },
                { parent ->
                    VH::class.java.constructors.first().newInstance(parent) as VH
                }
            )
        )

        return this
    }

    fun build(): GenericAdapter = GenericAdapter(items, binderHolders)

    fun <T> Class<T>.getLayoutId(): Int =
        annotations.filterIsInstance<LayoutId>().firstOrNull()?.layoutId ?: -1
}

abstract class Binder<in VH, in VO> {

    @Suppress("UNCHECKED_CAST")
    fun bindView(holder: RecyclerView.ViewHolder, data: Any, adapter: RecyclerView.Adapter<*>) {
        bind(holder as VH, data as VO, adapter)
    }

    abstract fun bind(holder: VH, data: VO, adapter: RecyclerView.Adapter<*>)
}

class BinderHolder<in VH, in VO>(
    val binder: Binder<VH, VO>,
    val layoutId: Int,
    val canHandleType: (Any) -> Boolean,
    val viewHolderFactory: (View) -> RecyclerView.ViewHolder
)

fun BinderHolder<*, *>.createViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
    viewHolderFactory(
        LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
    )