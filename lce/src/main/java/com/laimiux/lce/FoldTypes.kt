package com.laimiux.lce

inline fun <LoadingT, ContentT, ErrorT, T> LCE<LoadingT, ContentT, ErrorT>.foldTypes(
    crossinline onLoading: (Type.Loading<LoadingT>) -> T,
    crossinline onContent: (Type.Content<ContentT>) -> T,
    crossinline onError: (Type.Error<ErrorT>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading -> onLoading(type)
        is Type.Content -> onContent(type)
        is Type.Error -> onError(type)
    }
}

inline fun <ContentT, ErrorT, T> UCE<ContentT, ErrorT>.foldTypes(
    crossinline onLoading: (Type.Loading.UnitType) -> T,
    crossinline onContent: (Type.Content<ContentT>) -> T,
    crossinline onError: (Type.Error<ErrorT>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Content -> onContent(type)
        is Type.Error -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <ContentT, T> UCT<ContentT>.foldTypes(
    crossinline onLoading: (Type.Loading.UnitType) -> T,
    crossinline onContent: (Type.Content<ContentT>) -> T,
    crossinline onError: (Type.Error.ThrowableType) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Content -> onContent(type)
        is Type.Error.ThrowableType -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <ContentT, ErrorT, T> CE<ContentT, ErrorT>.foldTypes(
    crossinline onContent: (Type.Content<ContentT>) -> T,
    crossinline onError: (Type.Error<ErrorT>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Content -> onContent(type)
        is Type.Error -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <ContentT, T> CT<ContentT>.foldTypes(
    crossinline onContent: (Type.Content<ContentT>) -> T,
    crossinline onError: (Type.Error.ThrowableType) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Content -> onContent(type)
        is Type.Error.ThrowableType -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <ContentT, T> UC<ContentT>.foldTypes(
    crossinline onLoading: (Type.Loading.UnitType) -> T,
    crossinline onContent: (Type.Content<ContentT>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Content -> onContent(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}