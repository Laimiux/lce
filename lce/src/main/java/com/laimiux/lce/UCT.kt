package com.laimiux.lce

/**
 * UCT stands for Unit / Content / Throwable. A type that represents either loading state of
 * type Unit, content state of type [C] or error state of [Throwable] type.
 */
interface UCT<out C> {
    companion object {
        fun <T> content(content: T) = Type.Content(content)

        fun error(error: Throwable) = Type.ThrowableError(error)

        fun loading() = Type.UnitLoading
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
        return when (val type = asLceType()) {
            is Type.Content -> type
            is Type.ThrowableError -> type
            else -> null
        }
    }

    /**
     * Returns null when current state is loading otherwise returns [CE].
     */
    fun asCE(): CE<C, Throwable>? {
        return when (val type = asLceType()) {
            is Type.Content -> type
            is Type.ThrowableError -> type
            else -> null
        }
    }
}

fun <T> T.toUCT() = UCT.content(this)

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, NewT> UCT<ContentT>.mapContent(
    crossinline map: (ContentT) -> NewT
): UCT<NewT> {
    return when (val type = asLceType()) {
        is Type.Content -> UCT.content(map(type.value))
        else -> this as UCT<NewT>
    }
}

inline fun <ContentT> UCT<ContentT>.mapError(
    crossinline map: (Throwable) -> Throwable
): UCT<ContentT> {
    return when (val type = asLceType()) {
        is Type.ThrowableError -> UCT.error(map(type.value))
        is Type.Error -> UCT.error(map(type.value))
        else -> this
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <F, T> UCT<F>.flatMapContent(transform: (F) -> UCT<T>): UCT<T> {
    return when (val type = asLceType()) {
        is Type.Content -> transform(type.value)
        else -> this as UCT<T>
    }
}

inline fun <T> UCT<T>.flatMapLoading(
    crossinline map: () -> UCT<T>
): UCT<T> {
    return fold(
        onLoading = { map() },
        onError = { this },
        onContent = { this }
    )
}

inline fun <T> UCT<T>.flatMapError(
    crossinline map: (Throwable) -> UCT<T>
): UCT<T> {
    return fold(
        onError = map,
        onLoading = { this },
        onContent = { this }
    )
}


