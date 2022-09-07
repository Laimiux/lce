package com.laimiux.lce

/**
 * UT stands for Unit / Throwable. A type that represents either loading of type [Unit]
 * or error of type [Throwable].
 *
 * Note: It's an intermediary type to enable certain APIs and should rarely be used.
 */
interface UT {

    fun isLoading(): Boolean
    fun isError(): Boolean

    fun loadingOrNull(): Any?
    fun errorOrNull(): Throwable?

    fun asLceType(): Type<Any?, Nothing, Throwable>
}