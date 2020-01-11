package com.maleev.learning.a50androidhacks.hacks

import androidx.fragment.app.Fragment
import com.maleev.learning.a50androidhacks.utils.annotations.Description
import com.maleev.learning.a50androidhacks.utils.annotations.Number

@Number(1)
@Description("Centering Views using weights")
class Hack1Fragment : Fragment() {
    companion object {
        @Suppress("unused")
        fun newInstance() = Hack1Fragment()
    }
}