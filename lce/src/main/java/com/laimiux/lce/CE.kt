package com.laimiux.lce

/**
 * CE stands for Content / Error. A type that represents either content state of type [C] or
 * error state of type [E].
 */
interface CE<out C, out E> {
    companion object {
        fun <T> content(content: T) = Type.Content(content)

        fun error(error: Throwable) = Type.ThrowableError(error)
        fun <T> error(error: T) = Type.Error(error)
    }

    fun isContent(): Boolean
    fun isError(): Boolean

    fun contentOrNull(): C?
    fun errorOrNull(): E?

    fun asLceType(): Type<Any?, C, E>
}

inline fun <ContentT> CE<ContentT, Throwable>.asCT(): CT<ContentT> {
    return when(val type = asLceType()) {
        is Type.Content -> type
        is Type.ThrowableError -> type
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <ContentT> CE<ContentT, Throwable>.asUCT(): UCT<ContentT> {
    return when(val type = asLceType()) {
        is Type.Content -> type
        is Type.ThrowableError -> type
        else -> throw IllegalStateException("this should not happen: $type")
    }
}