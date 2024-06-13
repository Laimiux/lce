package com.laimiux.lce

/**
 * CT stands for Content / Throwable. A type that represents content state of type [C] or
 * error state of [Throwable] type.
 */
sealed interface CT<out C> {
    companion object {
        fun <T> content(content: T): CT<T> = Type.Content(content)
        fun error(error: Throwable): CT<Nothing> = Type.Error(error)

        /**
         * Returns [onNull] if [content] is null, otherwise returns [CT.content].
         */
        inline fun <L, C : Any, E> fromNullable(
            content: C?,
            crossinline onNull: () -> CT<C>
        ): CT<C> {
            return if (content == null) {
                onNull()
            } else {
                content(content)
            }
        }
    }

    fun isContent(): Boolean
    fun isError(): Boolean

    fun contentOrNull(): C?
    fun errorOrNull(): Throwable?

    fun asLceType(): Type<Any?, C, Throwable>
}
