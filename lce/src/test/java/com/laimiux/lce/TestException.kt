package com.laimiux.lce

/**
 * Exception used in tests that has predictable toString() and equality.
 */
data class TestException(val text: Any): Throwable() {
    override fun toString(): String = text.toString()
}