package com.maleev.learning.a50androidhacks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.maleev.learning.a50androidhacks.adapter.HackInfoVO
import com.maleev.learning.a50androidhacks.adapter.HackListAdapter
import com.maleev.learning.a50androidhacks.hacks.Hack1Fragment
import com.maleev.learning.a50androidhacks.hacks.Hack2Fragment
import com.maleev.learning.a50androidhacks.hacks.Hack3Fragment
import com.maleev.learning.a50androidhacks.hacks.Hack4Fragment

class HackListFragment : Fragment() {

    private val hacks = listOf(
        Hack1Fragment::class,
        Hack2Fragment::class,
        Hack3Fragment::class,
        Hack4Fragment::class
    ).map(toHackInfo)

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
        recycler.adapter = HackListAdapter(hacks.map { HackInfoVO(it) }) { fragmentInfo ->
            (activity as MainActivity).showFragment(fragmentInfo.creator())
        }
    }

    companion object {
        fun newInstance() = HackListFragment()
    }
}