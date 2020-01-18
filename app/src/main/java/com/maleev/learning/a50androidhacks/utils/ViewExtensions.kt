package com.maleev.learning.a50androidhacks.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View


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

fun Int.pxToDp(context: Context): Int = (this / context.resources.displayMetrics.density).toInt()

fun Int.dpToPx(context: Context): Int = (this * context.resources.displayMetrics.density).toInt()

fun Int.pxToSp(context: Context): Int =
    (this / context.resources.displayMetrics.scaledDensity).toInt()

fun Int.spToPx(context: Context): Int =
    (this * context.resources.displayMetrics.scaledDensity).toInt()

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.setVisible(isVisible: Boolean) {
    if (isVisible) show() else hide()
}