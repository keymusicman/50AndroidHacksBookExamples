package com.maleev.learning.a50androidhacks.hacks

import android.os.Bundle
import android.view.View
import android.view.ViewStub
import android.widget.TextView
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.utils.annotations.HackDescription
import com.maleev.learning.a50androidhacks.utils.annotations.HackNumber

@HackNumber(2)
@HackDescription("Using lazy loading and avoiding replication")
class Hack2Fragment : BaseHackFragment() {
    override val contentLayoutId: Int = R.layout.hack2_content
    override val settingsLayoutId: Int = R.layout.hack2_settings

    private var counter1: Int = 0
    private var counter2: Int = 0
    private var viewStubIsInflated = false

    private val includedLayout1: View by lazy { view!!.findViewById<View>(R.id.included_layout_1) }
    private val includedLayout2: View by lazy { view!!.findViewById<View>(R.id.included_layout_2) }
    private val tvLayoutCounter1: TextView by lazy { includedLayout1.findViewById<TextView>(R.id.tvCounter) }
    private val tvLayoutCounter2: TextView by lazy { includedLayout2.findViewById<TextView>(R.id.tvCounter) }
    private val viewStub: ViewStub by lazy { view!!.findViewById<ViewStub>(R.id.viewStub) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<View>(R.id.btn_include_1_increase).setOnClickListener {
            tvLayoutCounter1.text = "${++counter1}"
        }
        view.findViewById<View>(R.id.btn_include_2_increase).setOnClickListener {
            tvLayoutCounter2.text = "${++counter2}"
        }
        view.findViewById<View>(R.id.btn_view_stub_load).setOnClickListener {
            if (!viewStubIsInflated) {
                viewStub.inflate()
                viewStubIsInflated = true
            }
        }
    }

    companion object {
        @Suppress("unused")
        fun newInstance() = Hack2Fragment()
    }
}