package com.maleev.learning.a50androidhacks.hacks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.maleev.learning.a50androidhacks.R

abstract class BaseHackFragment : Fragment() {
    companion object {
        const val NO_LAYOUT = -1
    }

    abstract val contentLayoutId: Int
    abstract val settingsLayoutId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hack_layout, container, false)
            .apply {
                contentLayoutId.takeIf { it != NO_LAYOUT }
                    ?.let {
                        inflater.inflate(it, findViewById(R.id.layoutHackContent), true)
                    }

                settingsLayoutId.takeIf { it != NO_LAYOUT }
                    ?.let {
                        inflater.inflate(it, findViewById(R.id.layoutSettings), true)
                    }
            }
    }
}