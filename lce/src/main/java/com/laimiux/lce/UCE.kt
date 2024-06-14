package com.laimiux.lce

/**
 * UCE stands for Unit / Content / Error. A type that represents either loading state of type [Unit],
 * content state of type [C] or error state of type [E].
 */
interface UCE<out C, out E> {
    companion object {
        fun loading(): UCE<Nothing, Nothing> = Type.Loading()
        fun <T> content(value: T): UCE<T, Nothing> = Type.Content(value)
        fun <T> error(error: T): UCE<Nothing, T> = Type.Error(error)

        /**
         * Returns [UCE.loading] if [content] is null, otherwise returns [UCE.content].
         */
        fun <C : Any> fromNullable(content: C?): UCE<C, Nothing> {
            return fromNullable(content) { loading() }
        }

        /**
         * Returns [onNull] if [content] is null, otherwise returns [UCE.content].
         */
        inline fun <C : Any, E> fromNullable(
            content: C?,
            onNull: () -> UCE<C, E>
        ): UCE<C, E> {
            return if (content == null) {
                onNull()
            } else {
                content(content)
            }
        }
    }

    fun isLoading(): Boolean
    fun isContent(): Boolean
    fun isError(): Boolean

    fun loadingOrNull(): Any?
    fun contentOrNull(): C?
    fun errorOrNull(): E?

    fun asLceType(): Type<Any?, C, E>
}
