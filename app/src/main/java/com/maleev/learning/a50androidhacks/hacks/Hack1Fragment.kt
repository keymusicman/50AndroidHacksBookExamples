package com.maleev.learning.a50androidhacks.hacks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.utils.annotations.Description
import com.maleev.learning.a50androidhacks.utils.annotations.Number

@Number(1)
@Description("Centering Views using weights")
class Hack1Fragment : Fragment() {

    private lateinit var element: View
    private lateinit var sbWeight: SeekBar
    private lateinit var tvWeight: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hack1_fragment, container, false)
    }

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
        (element.layoutParams as LinearLayout.LayoutParams).weight = weight
        tvWeight.text = "$weight"
    }

    companion object {
        @Suppress("unused")
        fun newInstance() = Hack1Fragment()
    }
}