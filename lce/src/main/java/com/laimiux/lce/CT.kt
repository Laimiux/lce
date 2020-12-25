package com.laimiux.lce

/**
 * CT stands for Content / Throwable. A type that represents content state of type [C] or
 * error state of [Throwable] type.
 */
interface CT<out C> {
    companion object {
        fun <T> content(content: T) = Type.Content(content)

        fun error(error: Throwable) = Type.ThrowableError(error)
    }

    fun isContent(): Boolean
    fun isError(): Boolean

    fun contentOrNull(): C?
    fun errorOrNull(): Throwable?

    fun asLceType(): Type<Any?, C, Throwable>
}
