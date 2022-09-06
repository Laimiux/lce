package com.laimiux.lce

/**
 * LT stands for Loading / Throwable. A type that represents either loading of type [L]
 * or error of type [Throwable].
 *
 * Note: It's an intermediary type to enable certain APIs and should rarely be used.
 */
interface LT<out L> {

    fun isLoading(): Boolean
    fun isError(): Boolean

    fun loadingOrNull(): L?
    fun errorOrNull(): Throwable?

    fun asLceType(): Type<L, Nothing, Throwable>
}