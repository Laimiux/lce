package com.laimiux.lce.test

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LCE
import com.laimiux.lce.Type
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.foldTypes

private fun <T> fail(type: Type<*, *, *>): T {
    throw AssertionError("$type is not error.")
}

fun <E> LCE<*, *, E>.assertError(): Type.Error<E> {
    return foldTypes(
        onError = { it },
        onOther = { fail(asLceType()) }
    )
}

fun <E> UCE<*, E>.assertError(): Type.Error<E> {
    return foldTypes(
        onError = { it },
        onOther = { fail(asLceType()) }
    )
}

fun UCT<*>.assertError(): Type.Error<Throwable> {
    return foldTypes(
        onError = { it },
        onOther = { fail(asLceType()) }
    )
}

fun <E> CE<*, E>.assertError(): Type.Error<E> {
    return foldTypes(
        onError = { it },
        onContent = { fail(asLceType()) }
    )
}

fun CT<*>.assertError(): Type.Error<Throwable> {
    return foldTypes(
        onError = { it },
        onContent = { fail(asLceType()) }
    )
}