package com.laimiux.lce

/**
 * UC stands for Unit / Content. A type that represents either loading state of type [Unit]
 * or content state of type [C].
 */
interface UC<out C> {
    companion object {
        fun loading(): UC<Nothing> = Type.Loading()
        fun <T> content(value: T): UC<T> = Type.Content(value)

        /**
         * Returns [UC.loading] if [content] is null, otherwise returns [UC.content].
         */
        fun <C : Any> fromNullable(content: C?): UC<C> {
            return if (content == null) {
                loading()
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
