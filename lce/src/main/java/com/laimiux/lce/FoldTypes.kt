package com.laimiux.lce


inline fun <ContentT, T> UCT<ContentT>.foldTypes(
    crossinline onLoading: (Type.UnitLoading) -> T,
    crossinline onContent: (Type.Content<ContentT>) -> T,
    crossinline onError: (Type.Error.ThrowableError) -> T
): T {
    return when (val type = asLceType()) {
        is Type.UnitLoading -> onLoading(type) 
        is Type.Content -> onContent(type)
        is Type.Error.ThrowableError -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}