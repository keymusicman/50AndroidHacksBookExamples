package com.maleev.learning.a50androidhacks.utils.annotations

import androidx.annotation.IntegerRes

@Target(AnnotationTarget.CLASS)
annotation class HackBottomLineLayout(@IntegerRes val layoutId: Int)

@Target(AnnotationTarget.CLASS)
annotation class HackBottomLineText(val text: String)