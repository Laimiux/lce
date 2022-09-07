package com.laimiux.lce

/**
 * UE stands for Unit / Error. A type that represents either loading of type [Unit]
 * or error of type [E].
 *
 * Note: It's an intermediary type to enable certain APIs and should rarely be used.
 */
interface UE<out E> {

    fun isLoading(): Boolean
    fun isError(): Boolean

    fun loadingOrNull(): Any?
    fun errorOrNull(): E?

    fun asLceType(): Type<Any?, Nothing, E>
}