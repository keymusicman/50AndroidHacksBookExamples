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
import com.maleev.learning.a50androidhacks.models.Chapter
import com.maleev.learning.a50androidhacks.models.Hack
import com.maleev.learning.a50androidhacks.utils.AdapterBuilder
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.functions

class HackListFragment : Fragment() {

    private val hacks = listOf(
        Hack(1, 1, "CENTERING VIEWS USING WEIGHTS"),
        Hack(2, 1, "USING LAZY LOADING AND AVOIDING REPLICATION"),
        Hack(3, 1, "CREATING A CUSTOM VIEWGROUP"),
        Hack(4, 1, "PREFERENCES HACKS"),
        Hack(5, 2, "SNAPPY TRANSITIONS WITH TEXTSWITCHER AND IMAGESWITCHER"),
        Hack(6, 2, "ADDING EYE CANDY TO YOUR VIEWGROUP’S CHILDREN"),
        Hack(7, 2, "DOING ANIMATIONS OVER THE CANVAS"),
        Hack(8, 2, "SLIDESHOW USING THE KEN BURNS EFFECT"),
        Hack(9, 3, "AVOIDING DATE VALIDATIONS WITH AN EDITTEXT FOR DATES"),
        Hack(10, 3, "FORMATTING A TEXTVIEW’S TEXT"),
        Hack(11, 3, "ADDING TEXT GLOWING EFFECTS"),
        Hack(12, 3, "ROUNDED BORDERS FOR BACKGROUNDS"),
        Hack(13, 3, "GETTING THE VIEW’S WIDTH AND HEIGHT IN THE ONCREATE() METHOD"),
        Hack(14, 3, "VIDEOVIEWS AND ORIENTATION CHANGES"),
        Hack(15, 3, "REMOVING THE BACKGROUND TO IMPROVE YOUR ACTIVITY STARTUP TIME"),
        Hack(16, 3, "TOAST’S POSITION HACK"),
        Hack(17, 3, "CREATING A WIZARD FORM USING A GALLERY"),
        Hack(18, 4, "REMOVING LOG STATEMENTS BEFORE RELEASING"),
        Hack(19, 4, "USING THE HIERARCHY VIEWER TOOL TO REMOVE UNNECESSARY VIEWS"),
        Hack(20, 5, "THE MODEL-VIEW-PRESENTER PATTERN"),
        Hack(21, 5, "BROADCASTRECEIVER FOLLOWING ACTIVITY’S LIFECYCLE"),
        Hack(22, 5, "ARCHITECTURE PATTERN USING ANDROID LIBRARIES"),
        Hack(23, 5, "THE SYNCADAPTER PATTERN"),
        Hack(24, 6, "HANDLING EMPTY LISTS"),
        Hack(25, 6, "CREATING FAST ADAPTERS WITH A VIEWHOLDER"),
        Hack(26, 6, "ADDING SECTION HEADERS TO A LISTVIEW"),
        Hack(27, 6, "COMMUNICATING WITH AN ADAPTER USING AN ACTIVITY AND A DELEGATE"),
        Hack(28, 6, "TAKING ADVANTAGE OF LISTVIEW’S HEADER"),
        Hack(29, 6, "HANDLING ORIENTATION CHANGES INSIDE A VIEWPAGER"),
        Hack(30, 6, "LISTVIEW’S CHOICEMODE"),
        Hack(31, 7, "ASPECT-ORIENTED PROGRAMMING IN ANDROID"),
        Hack(32, 7, "EMPOWERING YOUR APPLICATION USING COCOS2D-X"),
        Hack(33, 8, "RUNNING OBJECTIVE-C IN ANDROID"),
        Hack(34, 8, "USING SCALA INSIDE ANDROID"),
        Hack(35, 9, "FIRING UP MULTIPLE INTENTS"),
        Hack(36, 9, "GETTING USER INFORMATION WHEN RECEIVING FEEDBACK"),
        Hack(37, 9, "ADDING AN MP3 TO THE MEDIA CONTENTPROVIDER"),
        Hack(38, 9, "ADDING A REFRESH ACTION TO THE ACTION BAR"),
        Hack(39, 9, "GETTING DEPENDENCIES FROM THE MARKET"),
        Hack(40, 9, "LAST-IN-FIRST-OUT IMAGE LOADING"),
        Hack(41, 10, "BUILDING DATABASES WITH ORMLITE"),
        Hack(42, 10, "CREATING CUSTOM FUNCTIONS IN SQLITE"),
        Hack(43, 10, "BATCHING DATABASE OPERATIONS"),
        Hack(44, 11, "HANDLING LIGHTS-OUT MODE"),
        Hack(45, 11, "USING NEW APIS IN OLDER DEVICES"),
        Hack(46, 11, "BACKWARD-COMPATIBLE NOTIFICATIONS"),
        Hack(47, 11, "CREATING TABS WITH FRAGMENTS"),
        Hack(48, 12, "HANDLING DEPENDENCIES WITH APACHE MAVEN"),
        Hack(49, 12, "INSTALLING DEPENDENCIES IN A ROOTED DEVICE"),
        Hack(50, 12, "USING JENKINS TO DEAL WITH DEVICE DIVERSITY")
    )

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
                if (hackInfo.isReady) {
                    (activity as MainActivity).showFragment(

                        hackInfo.createFragment()
                    )
                }
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

                    result.add(HackVO(hack, hack.isReady()))
                }
        }

    private fun Hack.isReady(): Boolean {
        return try {
            Class.forName("com.maleev.learning.a50androidhacks.hacks.hack${number}.Hack${number}Fragment")
            true
        } catch (e: ClassNotFoundException) {
            false
        }
    }

    private fun HackVO.createFragment(): Fragment {
        return Class.forName("com.maleev.learning.a50androidhacks.hacks.hack${hackInfo.number}.Hack${hackInfo.number}Fragment")
            .kotlin.run {
            companionObject
                ?.functions
                ?.first { function -> function.name == "newInstance" }
                ?.call(companionObjectInstance) as Fragment
        }
    }

    companion object {
        fun newInstance() = HackListFragment()
    }
}