package com.laimiux.lce.test

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.Type
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT

fun <C> Type.Content<C>.assertValue(expected: C) {
    val found = this.value
    if (expected != found) {
        throw AssertionError("Expected content value: $expected; found: $found")
    }
}

fun <C> LCE<*, C, *>.assertContent(expected: C) {
    assertContent().assertValue(expected)
}

fun <C> UCE<C, *>.assertContent(expected: C) {
    assertContent().assertValue(expected)
}

fun <C> UCT<C>.assertContent(expected: C) {
    assertContent().assertValue(expected)
}

fun <C> CE<C, *>.assertContent(expected: C) {
    assertContent().assertValue(expected)
}

fun <C> CT<C>.assertContent(expected: C) {
    assertContent().assertValue(expected)
}

fun <C> LC<*, C>.assertContent(expected: C) {
    assertContent().assertValue(expected)
}

fun <C> UC<C>.assertContent(expected: C) {
    assertContent().assertValue(expected)
}
