package com.laimiux.lce

/**
 * Lce stands for Loading / Content / Error. A type that represents either
 * loading of type [L], content of type [C] or error of type [E].
 */
sealed interface LCE<out L, out C, out E> {
    companion object {
        fun loading(): LCE<Unit, Nothing, Nothing> = Type.Loading()
        fun loading(unit: Unit): LCE<Unit, Nothing, Nothing> = Type.Loading(unit)
        fun <T> loading(loading: T): LCE<T, Nothing, Nothing> = Type.Loading(loading)

        fun <T> content(content: T): LCE<Nothing, T, Nothing> = Type.Content(content)

        fun <T> error(error: T): LCE<Nothing, Nothing, T> = Type.Error(error)

        /**
         * Returns [LCE.loading] if [content] is null, otherwise returns [LCE.content].
         */
        fun <C : Any> fromNullable(content: C?): LCE<Unit, C, Nothing> {
            return fromNullable(content) { loading() }
        }

        /**
         * Returns [onNull] if [content] is null, otherwise returns [LCE.content].
         */
        inline fun <L, C : Any, E> fromNullable(
            content: C?,
            crossinline onNull: () -> LCE<L, C, E>
        ): LCE<L, C, E> {
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
    fun errorOrNull(): E?
    fun loadingOrNull(): L?

    fun asLceType(): Type<L, C, E>
}
