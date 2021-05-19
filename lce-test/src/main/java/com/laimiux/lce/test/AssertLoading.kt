package com.laimiux.lce.test

import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.Type
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.foldTypes

private fun <T> fail(type: Type<*, *, *>): T {
    throw AssertionError("$type is not loading.")
}

fun <L> LCE<L, *, *>.assertLoading(): Type.Loading<L> {
    return foldTypes(
        onLoading = { it },
        onOther = { fail(asLceType()) }
    )
}

fun UCE<*, *>.assertLoading(): Type.Loading<Unit> {
    return foldTypes(
        onLoading = { it },
        onOther = { fail(asLceType()) }
    )
}

fun UCT<*>.assertLoading(): Type.Loading<Unit> {
    return foldTypes(
        onLoading = { it },
        onOther = { fail(asLceType()) }
    )
}

fun <L> LC<L, *>.assertLoading(): Type.Loading<L> {
    return foldTypes(
        onLoading = { it },
        onContent = { fail(asLceType()) }
    )
}

fun UC<*>.assertLoading(): Type.Loading<Unit> {
    return foldTypes(
        onLoading = { it },
        onContent = { fail(asLceType()) }
    )
}