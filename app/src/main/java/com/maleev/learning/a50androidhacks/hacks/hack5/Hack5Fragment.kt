package com.maleev.learning.a50androidhacks.hacks.hack5

import android.os.Bundle
import android.view.View
import android.widget.*
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.hacks.BaseHackFragment

class Hack5Fragment : BaseHackFragment() {

    override val contentLayoutId: Int = R.layout.hack5_content
    override val settingsLayoutId: Int = NO_LAYOUT

    private var currentTwix = Twix.NONE
    private var leftCounter: Int = 0
        get() = ++field
    private var rightCounter: Int = 0
        get() = ++field

    private val image: ImageSwitcher by lazy { view!!.findViewById<ImageSwitcher>(R.id.image) }
    private val tvLeft: TextSwitcher by lazy { view!!.findViewById<TextSwitcher>(R.id.tv_left) }
    private val tvRight: TextSwitcher by lazy { view!!.findViewById<TextSwitcher>(R.id.tv_right) }
    private val btnLeft: Button by lazy { view!!.findViewById<Button>(R.id.btn_left) }
    private val btnRight: Button by lazy { view!!.findViewById<Button>(R.id.btn_right) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewSwitcher.ViewFactory { TextView(context).apply { textSize = 30f } }.let {
            tvLeft.setFactory(it)
            tvRight.setFactory(it)
        }

        ViewSwitcher.ViewFactory { ImageView(context) }.let {
            image.setFactory(it)
        }

        btnLeft.setOnClickListener {
            tvLeft.setText(leftCounter.toString())
            if (currentTwix != Twix.LEFT) {
                image.setImageResource(R.drawable.left_twix)
            }
            currentTwix = Twix.LEFT
        }

        btnRight.setOnClickListener {
            tvRight.setText(rightCounter.toString())
            if (currentTwix != Twix.RIGHT) {
                image.setImageResource(R.drawable.right_twix)
            }
            currentTwix = Twix.RIGHT
        }
    }

    private enum class Twix {
        NONE, LEFT, RIGHT
    }

    companion object {
        @Suppress("unused")
        fun newInstance() = Hack5Fragment()
    }
}