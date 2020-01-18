package com.maleev.learning.a50androidhacks.hacks.hack1

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.hacks.BaseHackFragment
import com.maleev.learning.a50androidhacks.utils.annotations.HackBottomLineText
import com.maleev.learning.a50androidhacks.utils.annotations.HackChapter
import com.maleev.learning.a50androidhacks.utils.annotations.HackDescription
import com.maleev.learning.a50androidhacks.utils.annotations.HackNumber

@HackChapter(1)
@HackNumber(1)
@HackBottomLineText(
    "Using LinearLayout’s weight is important when you want to distribute the available " +
            "space based on a percentage rather than using hard-coded sizes. If you’re targeting " +
            "Honeycomb and using Fragments, you’ll notice that most of the examples place the " +
            "different Fragments in a layout using weights. Understanding how to use weights will " +
            "add an important tool to your toolbox. "
)
@HackDescription("Centering Views using weights")
class Hack1Fragment : BaseHackFragment() {

    override val contentLayoutId: Int = R.layout.hack1_content
    override val settingsLayoutId: Int = R.layout.hack1_settings

    private lateinit var element: View
    private lateinit var sbWeight: SeekBar
    private lateinit var tvWeight: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        element = view.findViewById(R.id.element)
        sbWeight = view.findViewById(R.id.sbWeight)
        tvWeight = view.findViewById(R.id.tvWeight)

        sbWeight.progress = 25
        setWeight(0.25f)
        sbWeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                setWeight(progress / 100f)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    private fun setWeight(weight: Float) {
        element.layoutParams =
            (element.layoutParams as LinearLayout.LayoutParams).apply { this.weight = weight }
        tvWeight.text = "$weight"
    }

    companion object {
        @Suppress("unused")
        fun newInstance() = Hack1Fragment()
    }
}