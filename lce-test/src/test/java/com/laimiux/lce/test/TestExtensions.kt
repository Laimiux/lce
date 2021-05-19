package com.laimiux.lce.test

fun expectError(action: () -> Unit): Throwable {
    try {
        action()
        throw IllegalStateException("Action didn't throw exception")
    } catch (e: Throwable) {
        return e
    }
}