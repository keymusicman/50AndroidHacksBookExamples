package com.maleev.learning.a50androidhacks.models

class Chapter(
    val number: Int,
    val title: String
) {
    companion object {
        val UNKNOWN = Chapter(0, "Unknown")
    }
}