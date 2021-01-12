package com.laimiux.lce

/**
 * UC stands for Unit / Content. A type that represents either loading state of type [Unit]
 * or content state of type [C].
 */
interface UC<out C> {
    companion object {
        fun loading(): UC<Nothing> = Type.Loading()
        fun <C> content(value: C): UC<C> = Type.Content(value)

        /**
         * Returns [UC.loading] if [content] is null, otherwise returns [UC.content].
         */
        fun <C : Any> fromNullable(content: C?): UC<C> {
            return fromNullable(content) { loading() }
        }

        /**
         * Returns [onNull] if [content] is null, otherwise returns [UC.content].
         */
        inline fun <C : Any> fromNullable(
            content: C?,
            crossinline onNull: () -> UC<C>
        ): UC<C> {
            return if (content == null) {
                onNull()
            } else {
                content(content)
            }
        }
    }

    fun isLoading(): Boolean
    fun isContent(): Boolean

    fun loadingOrNull(): Any?
    fun contentOrNull(): C?

    fun asLceType(): Type<Any?, C, Nothing>
}
