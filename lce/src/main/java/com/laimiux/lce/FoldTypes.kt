package com.laimiux.lce

inline fun <L, C, E, T> LCE<L, C, E>.foldTypes(
    crossinline onLoading: (Type.Loading<L>) -> T,
    crossinline onContent: (Type.Content<C>) -> T,
    crossinline onError: (Type.Error<E>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading -> onLoading(type)
        is Type.Content -> onContent(type)
        is Type.Error -> onError(type)
    }
}

inline fun <C, E, T> UCE<C, E>.foldTypes(
    crossinline onLoading: (Type.Loading.UnitType) -> T,
    crossinline onContent: (Type.Content<C>) -> T,
    crossinline onError: (Type.Error<E>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Content -> onContent(type)
        is Type.Error -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <C, T> UCT<C>.foldTypes(
    crossinline onLoading: (Type.Loading.UnitType) -> T,
    crossinline onContent: (Type.Content<C>) -> T,
    crossinline onError: (Type.Error.ThrowableType) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Content -> onContent(type)
        is Type.Error.ThrowableType -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <C, E, T> CE<C, E>.foldTypes(
    crossinline onContent: (Type.Content<C>) -> T,
    crossinline onError: (Type.Error<E>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Content -> onContent(type)
        is Type.Error -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <C, T> CT<C>.foldTypes(
    crossinline onContent: (Type.Content<C>) -> T,
    crossinline onError: (Type.Error.ThrowableType) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Content -> onContent(type)
        is Type.Error.ThrowableType -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <L, C, T> LC<L, C>.foldTypes(
    crossinline onLoading: (Type.Loading<L>) -> T,
    crossinline onContent: (Type.Content<C>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading -> onLoading(type)
        is Type.Content -> onContent(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <C, T> UC<C>.foldTypes(
    crossinline onLoading: (Type.Loading.UnitType) -> T,
    crossinline onContent: (Type.Content<C>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Content -> onContent(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}