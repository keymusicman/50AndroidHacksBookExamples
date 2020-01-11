package com.maleev.learning.a50androidhacks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.maleev.learning.a50androidhacks.hacks.Hack1Fragment
import com.maleev.learning.a50androidhacks.utils.annotations.Description
import com.maleev.learning.a50androidhacks.utils.annotations.Number
import com.maleev.learning.a50androidhacks.utils.genericAdapter
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.functions

class HackListFragment : Fragment() {
    companion object {
        fun newInstance() = HackListFragment()
    }

    private val toFragmentInfo: KClass<*>.() -> FragmentInfo = {
        FragmentInfo(getNumber(), getDescription()) {
            companionObject
                ?.functions
                ?.first { function -> function.name == "newInstance" }
                ?.call(companionObjectInstance) as Fragment
        }
    }

    private val hacks = listOf(
        Hack1Fragment::class
    ).map(toFragmentInfo)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hacklist, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.recycler)
        recycler.adapter =
            genericAdapter<FragmentInfo, HackViewHolder>(
                R.layout.layout_hack_item,
                hacks
            ) { viewHolder, fragmentInfo ->
                viewHolder.tvHackNumber.text = "Hack ${fragmentInfo.number}"
                viewHolder.tvHackDescription.text = fragmentInfo.description

                viewHolder.itemView.setOnClickListener {
                    (activity as MainActivity).showFragment(fragmentInfo.creator())
                }
            }
    }

    private class FragmentInfo(
        val number: Int,
        val description: String,
        val creator: () -> Fragment
    )

    private fun KClass<*>.getDescription(): String {
        return annotations.filterIsInstance<Description>().firstOrNull()?.description ?: ""
    }

    private fun KClass<*>.getNumber(): Int {
        return (annotations.filterIsInstance<Number>().firstOrNull())?.number ?: 0
    }
}