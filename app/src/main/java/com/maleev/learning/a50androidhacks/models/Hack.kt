package com.maleev.learning.a50androidhacks.models

import com.maleev.learning.a50androidhacks.adapter.BottomLineProvider
import com.maleev.learning.a50androidhacks.utils.annotations.*
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation

class Hack(
    val number: Int,
    val chapterNumber: Int,
    val description: String
)

val toHack: KClass<*>.() -> Hack = {
    Hack(
        getNumber(),
        getChapter(),
        getDescription()
    )
}

private fun KClass<*>.getDescription(): String =
    findAnnotation<HackDescription>()?.description ?: ""

private fun KClass<*>.getNumber(): Int =
    findAnnotation<HackNumber>()?.number ?: 0

private fun KClass<*>.getChapter(): Int =
    findAnnotation<HackChapter>()?.run { number } ?: 0

private fun KClass<*>.getBottomLineProvider(): BottomLineProvider? =
    findAnnotation<HackBottomLineLayout>()?.let {
        BottomLineProvider.BottomLineLayoutProvider(it.layoutId)
    } ?: findAnnotation<HackBottomLineText>()?.let {
        BottomLineProvider.BottomLineTextProvider(it.text)
    }