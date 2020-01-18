package com.maleev.learning.a50androidhacks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.maleev.learning.a50androidhacks.adapter.HackInfoBinder
import com.maleev.learning.a50androidhacks.adapter.HackInfoVO
import com.maleev.learning.a50androidhacks.hacks.hack1.Hack1Fragment
import com.maleev.learning.a50androidhacks.hacks.hack2.Hack2Fragment
import com.maleev.learning.a50androidhacks.hacks.hack3.Hack3Fragment
import com.maleev.learning.a50androidhacks.hacks.hack4.Hack4Fragment
import com.maleev.learning.a50androidhacks.utils.AdapterBuilder

class HackListFragment : Fragment() {

    private val hacks = listOf(
        Hack1Fragment::class,
        Hack2Fragment::class,
        Hack3Fragment::class,
        Hack4Fragment::class
    )
        .map(toHackInfo)
        .map { HackInfoVO(it) }

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
            AdapterBuilder(hacks)
                .withItem(HackInfoBinder { hackInfo ->
                    (activity as MainActivity).showFragment(
                        hackInfo.creator()
                    )
                })
                .build()

    }

    companion object {
        fun newInstance() = HackListFragment()
    }
}