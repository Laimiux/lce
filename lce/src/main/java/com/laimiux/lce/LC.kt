package com.laimiux.lce

/**
 * LC stands for Loading / Content. A type that represents either
 * loading of type [L] or content of type [C].
 */
interface LC<out L, out C> {
    companion object {
        fun loading(): LC<Unit, Nothing> = Type.Loading()
        fun loading(unit: Unit): LC<Unit, Nothing> = Type.Loading(unit)
        fun <T> loading(loading: T): LC<T, Nothing> = Type.Loading(loading)

        fun <T> content(content: T): LC<Nothing, T> = Type.Content(content)
    }

    fun isLoading(): Boolean
    fun isContent(): Boolean

    fun contentOrNull(): C?
    fun loadingOrNull(): L?

    fun asLceType(): Type<L, C, Nothing>
}
