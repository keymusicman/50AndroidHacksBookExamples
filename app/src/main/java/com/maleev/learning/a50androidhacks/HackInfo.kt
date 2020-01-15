package com.maleev.learning.a50androidhacks

import androidx.fragment.app.Fragment
import com.maleev.learning.a50androidhacks.adapter.BottomLineProvider
import com.maleev.learning.a50androidhacks.utils.annotations.HackBottomLineLayout
import com.maleev.learning.a50androidhacks.utils.annotations.HackBottomLineText
import com.maleev.learning.a50androidhacks.utils.annotations.HackDescription
import com.maleev.learning.a50androidhacks.utils.annotations.HackNumber
import kotlin.reflect.KClass
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.functions

class HackInfo(
    val number: Int,
    val description: String,
    val bottomLineProvider: BottomLineProvider?,
    val creator: () -> Fragment
)

val toHackInfo: KClass<*>.() -> HackInfo = {
    HackInfo(
        getNumber(),
        getDescription(),
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

private fun KClass<*>.getBottomLineProvider(): BottomLineProvider? =
    findAnnotation<HackBottomLineLayout>()?.let {
        BottomLineProvider.BottomLineLayoutProvider(it.layoutId)
    } ?: findAnnotation<HackBottomLineText>()?.let {
        BottomLineProvider.BottomLineTextProvider(it.text)
    }