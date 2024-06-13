package com.laimiux.lce

/**
 * UCT stands for Unit / Content / Throwable. A type that represents either loading state of
 * type Unit, content state of type [C] or error state of [Throwable] type.
 */
sealed interface UCT<out C> {
    companion object {
        fun loading(): UCT<Nothing> = Type.Loading()

        fun <T> content(content: T): UCT<T> = Type.Content(content)

        fun error(error: Throwable): UCT<Nothing> = Type.Error(error)

        /**
         * Returns [UCT.loading] if [content] is null, otherwise returns [UCT.content].
         */
        fun <C : Any> fromNullable(content: C?): UCT<C> {
            return fromNullable(content) { loading() }
        }

        /**
         * Returns [onNull] if [content] is null, otherwise returns [UCT.content].
         */
        inline fun <C : Any> fromNullable(
            content: C?,
            crossinline onNull: () -> UCT<C>
        ): UCT<C> {
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

    fun contentOrNull(): C?
    fun errorOrNull(): Throwable?
    fun loadingOrNull(): Any?

    fun asLceType(): Type<Any?, C, Throwable>
}
