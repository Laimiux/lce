package com.laimiux.lce.test

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LCE
import com.laimiux.lce.Type
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT

fun <E> Type.Error<E>.assertValue(expected: E) {
    val found = this.value
    if (expected != found) {
        throw AssertionError("Expected error value: $expected; found: $found")
    }
}

fun <E> LCE<*, *, E>.assertError(expected: E) {
    assertError().assertValue(expected)
}

fun <E> UCE<*, E>.assertError(expected: E) {
    assertError().assertValue(expected)
}

fun UCT<*>.assertError(expected: Throwable) {
    assertError().assertValue(expected)
}

fun <E> CE<*, E>.assertError(expected: E) {
    assertError().assertValue(expected)
}

fun CT<*>.assertError(expected: Throwable) {
    assertError().assertValue(expected)
}
