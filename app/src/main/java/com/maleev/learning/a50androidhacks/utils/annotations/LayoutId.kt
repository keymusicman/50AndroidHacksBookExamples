package com.maleev.learning.a50androidhacks.utils.annotations

import androidx.annotation.IntegerRes

@Target(AnnotationTarget.CLASS)
annotation class LayoutId(@IntegerRes val layoutId: Int)