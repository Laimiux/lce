package com.laimiux.lce

/**
 * UCE stands for Unit / Content / Error. A type that represents either loading state of type [Unit],
 * content state of type [C] or error state of type [E].
 */
interface UCE<out C, out E> {
    companion object {
        fun loading(): UCE<Nothing, Nothing> = Type.Loading()
        fun <T> content(value: T): UCE<T, Nothing> = Type.Content(value)
        fun error(error: Throwable): UCE<Nothing, Throwable> = Type.Error.ThrowableType(error)
        fun <T> error(error: T): UCE<Nothing, T> = Type.Error(error)
    }

    fun isLoading(): Boolean
    fun isContent(): Boolean

    fun loadingOrNull(): Any?
    fun contentOrNull(): C?

    fun asLceType(): Type<Any?, C, E>
}
