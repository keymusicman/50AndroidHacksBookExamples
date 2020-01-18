package com.maleev.learning.a50androidhacks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.maleev.learning.a50androidhacks.adapter.ChapterBinder
import com.maleev.learning.a50androidhacks.adapter.ChapterVO
import com.maleev.learning.a50androidhacks.adapter.HackBinder
import com.maleev.learning.a50androidhacks.adapter.HackVO
import com.maleev.learning.a50androidhacks.hacks.hack1.Hack1Fragment
import com.maleev.learning.a50androidhacks.hacks.hack2.Hack2Fragment
import com.maleev.learning.a50androidhacks.hacks.hack3.Hack3Fragment
import com.maleev.learning.a50androidhacks.hacks.hack4.Hack4Fragment
import com.maleev.learning.a50androidhacks.models.Chapter
import com.maleev.learning.a50androidhacks.models.Hack
import com.maleev.learning.a50androidhacks.models.toHack
import com.maleev.learning.a50androidhacks.utils.AdapterBuilder

class HackListFragment : Fragment() {

    private val hacks = listOf(
        Hack1Fragment::class,
        Hack2Fragment::class,
        Hack3Fragment::class,
        Hack4Fragment::class
    ).map(toHack)

    private val chapters = listOf(
        Chapter(1, "Working your way around layouts"),
        Chapter(2, "Creating cool animations"),
        Chapter(3, "View tips and tricks"),
        Chapter(4, "Tools"),
        Chapter(5, "Patterns"),
        Chapter(6, "Working with lists and adapters"),
        Chapter(7, "Useful libraries"),
        Chapter(8, "Interacting with other languages"),
        Chapter(9, "Ready-to-use snippets"),
        Chapter(10, "Beyond database basics"),
        Chapter(11, "Avoiding fragmentation"),
        Chapter(12, "Building tools")
    )

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
        recycler.adapter = AdapterBuilder(makeBook(hacks, chapters))
            .withItem(HackBinder { hackInfo ->
                (activity as MainActivity).showFragment(
                    hackInfo.creator()
                )
            })
            .withItem(ChapterBinder())
            .build()
    }

    private fun makeBook(hacks: List<Hack>, chapters: List<Chapter>): List<Any> =
        mutableListOf<Any>().also { result ->
            var lastChapter = -1
            hacks
                .filter { it.chapterNumber >= 0 && it.number > 0 }
                .sortedWith(compareBy<Hack> { it.chapterNumber }.thenBy { it.number })
                .forEach { hack ->
                    if (lastChapter != hack.chapterNumber) {
                        val chapter = chapters.firstOrNull { it.number == hack.chapterNumber }
                            ?: Chapter.UNKNOWN

                        result.add(ChapterVO(chapter))
                        lastChapter = hack.chapterNumber
                    }

                    result.add(HackVO(hack))
                }
        }

    companion object {
        fun newInstance() = HackListFragment()
    }
}