package com.laimiux.lce

/**
 * Lce stands for Loading / Content / Error. A type that represents either
 * loading of type [L], content of type [C] or error of type [E].
 */
interface LCE<out L, out C, out E> {
    companion object {
        fun loading(): LCE<Unit, Nothing, Nothing> = Type.Loading()
        fun loading(unit: Unit): LCE<Unit, Nothing, Nothing> = Type.Loading(unit)
        fun <T> loading(loading: T): LCE<T, Nothing, Nothing> = Type.Loading(loading)

        fun <T> content(content: T): LCE<Nothing, T, Nothing> = Type.Content(content)

        fun error(error: Throwable): LCE<Nothing, Nothing, Throwable> = Type.Error(error)
        fun <T> error(error: T): LCE<Nothing, Nothing, T> = Type.Error(error)
    }

    fun isLoading(): Boolean
    fun isContent(): Boolean
    fun isError(): Boolean

    fun contentOrNull(): C?
    fun errorOrNull(): E?
    fun loadingOrNull(): L?

    fun asLceType(): Type<L, C, E>
}
