package com.laimiux.lce.test

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.Type
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.foldTypes

private fun <T> fail(type: Type<*, *, *>): T {
    throw AssertionError("$type is not content.")
}

fun <C> LCE<*, C, *>.assertContent(): Type.Content<C> {
    return foldTypes(
        onContent = { it },
        onOther = { fail(asLceType()) }
    )
}


fun <C> UCE<C, *>.assertContent(): Type.Content<C> {
    return foldTypes(
        onContent = { it },
        onOther = { fail(asLceType()) }
    )
}

fun <C> UCT<C>.assertContent(): Type.Content<C> {
    return foldTypes(
        onContent = { it },
        onOther = { fail(asLceType()) }
    )
}

fun <C> CE<C, *>.assertContent(): Type.Content<C> {
    return foldTypes(
        onContent = { it },
        onError = { fail(asLceType()) }
    )
}

fun <C> CT<C>.assertContent(): Type.Content<C> {
    return foldTypes(
        onContent = { it },
        onError = { fail(asLceType()) }
    )
}

fun <C> LC<*, C>.assertContent(): Type.Content<C> {
    return foldTypes(
        onContent = { it },
        onLoading = { fail(asLceType()) }
    )
}

fun <C> UC<C>.assertContent(): Type.Content<C> {
    return foldTypes(
        onContent = { it },
        onLoading = { fail(asLceType()) }
    )
}