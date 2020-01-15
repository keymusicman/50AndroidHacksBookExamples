package com.maleev.learning.a50androidhacks.hacks

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.SeekBar
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.utils.annotations.HackDescription
import com.maleev.learning.a50androidhacks.utils.annotations.HackNumber

@HackNumber(3)
@HackDescription("Creating a custom ViewGroup")
class Hack3Fragment : BaseHackFragment() {
    override val contentLayoutId: Int = R.layout.hack3_content
    override val settingsLayoutId: Int = R.layout.hack3_settings

    private val sbOffset: SeekBar by lazy { view!!.findViewById<SeekBar>(R.id.sbOffset) }
    private val cascadeLayout: CascadeLayout by lazy { view!!.findViewById<CascadeLayout>(R.id.cascadeLayout) }
    private val rbHorizontalOffset: RadioButton by lazy { view!!.findViewById<RadioButton>(R.id.radioHorizontalOffset) }
    private val rbLayoutOffset: RadioButton by lazy { view!!.findViewById<RadioButton>(R.id.radioLayoutOffset) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sbOffset.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (rbLayoutOffset.isChecked) {
                    // update CascadeLayout offset
                    if (rbHorizontalOffset.isChecked) {
                        cascadeLayout.horizontalSpacing = progress
                    } else {
                        cascadeLayout.verticalSpacing = progress
                    }
                } else {
                    // update first child offset
                    val layoutParams =
                        cascadeLayout.getChildAt(0).layoutParams as CascadeLayout.LayoutParams

                    if (rbHorizontalOffset.isChecked) {
                        layoutParams.horizontalSpacing = progress
                    } else {
                        layoutParams.verticalSpacing = progress
                    }

                    cascadeLayout.getChildAt(0).layoutParams = layoutParams
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }

    companion object {
        @Suppress("unused")
        fun newInstance() = Hack3Fragment()
    }
}