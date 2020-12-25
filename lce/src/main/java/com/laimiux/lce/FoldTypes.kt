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

inline fun <ContentT, T> UCT<ContentT>.foldTypes(
    crossinline onLoading: (Type.Loading.Unit) -> T,
    crossinline onContent: (Type.Content<ContentT>) -> T,
    crossinline onError: (Type.Error.ThrowableError) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.Unit -> onLoading(type)
        is Type.Content -> onContent(type)
        is Type.Error.ThrowableError -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}