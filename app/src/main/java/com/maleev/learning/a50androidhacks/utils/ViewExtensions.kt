package com.maleev.learning.a50androidhacks.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet

@SuppressLint("Recycle")
fun Context.obtainStyledAttributes(
    attrs: AttributeSet,
    styleableRes: IntArray,
    readAttributes: TypedArray.() -> Unit
) {
    obtainStyledAttributes(attrs, styleableRes)
        .run {
            try {
                readAttributes()
            } finally {
                recycle()
            }
        }
}

fun Int.toPx(context: Context): Int = (this * context.resources.displayMetrics.density).toInt()

fun Int.toDp(context: Context): Int = (this / context.resources.displayMetrics.density).toInt()