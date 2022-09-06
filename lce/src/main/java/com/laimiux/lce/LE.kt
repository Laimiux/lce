package com.laimiux.lce

/**
 * LT stands for Loading / Error. A type that represents either loading of type [L]
 * or error of type [E].
 *
 * Note: It's an intermediary type to enable certain APIs and should rarely be used.
 */
interface LE<out L, out E> {

    fun isLoading(): Boolean
    fun isError(): Boolean

    fun loadingOrNull(): L?
    fun errorOrNull(): E?

    fun asLceType(): Type<L, Nothing, E>
}