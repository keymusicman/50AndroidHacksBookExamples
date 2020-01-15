package com.maleev.learning.a50androidhacks.hacks.hack4

import android.content.Context
import android.util.AttributeSet
import androidx.preference.DialogPreference

class AboutDialog : DialogPreference {
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?) : super(context)
}