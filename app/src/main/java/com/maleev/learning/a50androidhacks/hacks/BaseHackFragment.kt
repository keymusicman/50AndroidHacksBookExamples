package com.maleev.learning.a50androidhacks.hacks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maleev.learning.a50androidhacks.R

abstract class BaseHackFragment : Fragment() {
    abstract val contentLayoutId: Int
    abstract val settingsLayoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hack_layout, container, false)
            .apply {
                inflater.inflate(contentLayoutId, findViewById(R.id.layoutHackContent), true)
                inflater.inflate(settingsLayoutId, findViewById(R.id.layoutSettings), true)
            }
    }
}