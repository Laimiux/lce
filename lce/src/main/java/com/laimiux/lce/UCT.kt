package com.laimiux.lce

/**
 * UCT stands for Unit / Content / Throwable. A type that represents either loading state of
 * type Unit, content state of type [C] or error state of [Throwable] type.
 */
interface UCT<out C> {
    companion object {
        fun <T> content(content: T) = Type.Content(content)

        fun error(error: Throwable) = Type.Error(error)

        fun loading() = Type.Loading()
    }

    fun isLoading(): Boolean
    fun isContent(): Boolean
    fun isError(): Boolean

    fun contentOrNull(): C?
    fun errorOrNull(): Throwable?
    fun loadingOrNull(): Any?

    fun asLceType(): Type<Any?, C, Throwable>

    /**
     * Returns null when current state is loading otherwise returns [CT].
     */
    fun asCT(): CT<C>? {
        return foldTypes(
            onLoading = { null },
            onContent = { it },
            onError = { it }
        )
    }

    /**
     * Returns null when current state is loading otherwise returns [CE].
     */
    fun asCE(): CE<C, Throwable>? {
        return foldTypes(
            onLoading = { null },
            onContent = { it },
            onError = { it }
        )
    }
}
