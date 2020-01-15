package com.maleev.learning.a50androidhacks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

sealed class BottomLineProvider {
    abstract fun onCreateBottomLineView(parent: ViewGroup): View

    class BottomLineLayoutProvider(val layoutId: Int) : BottomLineProvider() {
        override fun onCreateBottomLineView(parent: ViewGroup): View {
            return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        }
    }

    class BottomLineTextProvider(val text: String) : BottomLineProvider() {
        override fun onCreateBottomLineView(parent: ViewGroup): View {
            return TextView(parent.context).apply {
                this.text = this@BottomLineTextProvider.text
            }
        }
    }
}