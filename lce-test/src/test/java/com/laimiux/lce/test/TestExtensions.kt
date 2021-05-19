package com.laimiux.lce.test

import com.google.common.truth.Truth

fun expectError(action: () -> Unit): Throwable {
    try {
        action()
        throw IllegalStateException("Action didn't throw exception")
    } catch (e: Throwable) {
        Truth.assertThat(e).isInstanceOf(AssertionError::class.java)
        return e
    }
}