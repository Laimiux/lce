package com.laimiux.lce.test

import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.Type

private fun <T> Type.Loading<T>.assertValue(expected: T) {
    val current = this.value
    if (expected != current) {
        throw AssertionError("Expected loading value: $expected; found: $current")
    }
}

fun <L> LCE<L, *, *>.assertLoading(expected: L) {
    assertLoading().assertValue(expected)
}

fun <L> LC<L, *>.assertLoading(expected: L) {
    assertLoading().assertValue(expected)
}
