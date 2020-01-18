package com.maleev.learning.a50androidhacks.models

import androidx.fragment.app.Fragment
import com.maleev.learning.a50androidhacks.adapter.BottomLineProvider
import com.maleev.learning.a50androidhacks.utils.annotations.*
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions

class Hack(
    val number: Int,
    val description: String,
    val chapterNumber: Int,
    val bottomLineProvider: BottomLineProvider?,
    val creator: () -> Fragment
)

val toHack: KClass<*>.() -> Hack = {
    Hack(
        getNumber(),
        getDescription(),
        getChapter(),
        getBottomLineProvider()
    ) {
        companionObject
            ?.functions
            ?.first { function -> function.name == "newInstance" }
            ?.call(companionObjectInstance) as Fragment
    }
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