package com.maleev.learning.a50androidhacks.hacks.hack4

import android.os.Bundle
import androidx.preference.EditTextPreferenceDialogFragmentCompat
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.maleev.learning.a50androidhacks.R
import com.maleev.learning.a50androidhacks.utils.annotations.HackBottomLineText
import com.maleev.learning.a50androidhacks.utils.annotations.HackChapter
import com.maleev.learning.a50androidhacks.utils.annotations.HackDescription
import com.maleev.learning.a50androidhacks.utils.annotations.HackNumber

@HackChapter(1)
@HackNumber(4)
@HackBottomLineText(
    "Although the settings framework allows you to add some custom behavior, you need to " +
            "remember that its purpose is to create simple preferences screens. If you’re thinking " +
            "of adding more complex user interfaces or flows, I’d recommend you create a separate Activity, " +
            "theming it as a Dialog, and launching it from a preferences widget. "
)
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