package com.maleev.learning.a50androidhacks.hacks

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.maleev.learning.a50androidhacks.utils.annotations.HackDescription
import com.maleev.learning.a50androidhacks.utils.annotations.HackNumber

@HackNumber(4)
@HackDescription("Preferences hacks")
class Hack4Fragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    }

    companion object {
        @Suppress("unused")
        fun newInstance() = Hack4Fragment()
    }
}