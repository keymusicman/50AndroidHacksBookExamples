package com.maleev.learning.a50androidhacks.hacks.hack4

import android.os.Bundle
import androidx.preference.EditTextPreferenceDialogFragmentCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.utils.annotations.HackDescription
import com.maleev.learning.a50androidhacks.utils.annotations.HackNumber

@HackNumber(4)
@HackDescription("Preferences hacks")
class Hack4Fragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.hack4_preferences)
    }

    override fun onDisplayPreferenceDialog(preference: Preference) {
        if (preference is EmailDialog) {
            val dialogFragment = EditTextPreferenceDialogFragmentCompat.newInstance(preference.key)
            dialogFragment.setTargetFragment(this, 0)
            dialogFragment.show(fragmentManager!!, null)
        }
    }

    companion object {
        @Suppress("unused")
        fun newInstance() = Hack4Fragment()
    }
}