package com.laimiux.lce

/**
 * Lce stands for Loading / Content / Error. A type that represents either
 * loading of type [L], content of type [C] or error of type [E].
 */
interface LCE<out L, out C, out E> {
    companion object {
        fun <T> content(content: T) = Type.Content(content)

        fun error(error: Throwable) = Type.ThrowableError(error)
        fun <T> error(error: T) = Type.Error(error)

        fun loading() = Type.UnitLoading
        fun loading(unit: Unit) = Type.UnitLoading
        fun <T> loading(loading: T) = Type.Loading(loading)
    }

    fun isLoading(): Boolean
    fun isContent(): Boolean
    fun isError(): Boolean

    fun contentOrNull(): C?
    fun errorOrNull(): E?
    fun loadingOrNull(): L?

    fun asLceType(): Type<L, C, E>
}



@Suppress("UNCHECKED_CAST")
inline fun <LoadingT, ContentT, ErrorT, NewT> LCE<LoadingT, ContentT, ErrorT>.mapContent(
    crossinline map: (ContentT) -> NewT
): LCE<LoadingT, NewT, ErrorT> {
    return when (val type = asLceType()) {
        is Type.Content -> LCE.content(map(type.value))
        else -> this as LCE<LoadingT, NewT, ErrorT>
    }
}

fun <ContentT> LCE<Unit, ContentT, Throwable>.asUCT(): UCT<ContentT> {
    return when (val type = asLceType()) {
        is Type.Content -> type
        is Type.ThrowableError -> type
        is Type.UnitLoading -> type
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

