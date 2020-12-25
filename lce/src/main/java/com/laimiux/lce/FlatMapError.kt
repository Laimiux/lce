package com.laimiux.lce

inline fun <LoadingT, ContentT, ErrorT> LCE<LoadingT, ContentT, ErrorT>.flatMapError(
    crossinline map: (ErrorT) -> LCE<LoadingT, ContentT, ErrorT>
): LCE<LoadingT, ContentT, ErrorT> {
    return fold(
        onError = map,
        onContent = { this },
        onLoading = { this }
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

inline fun <ContentT, ErrorT> CE<ContentT, ErrorT>.flatMapError(
    crossinline map: (ErrorT) -> CE<ContentT, ErrorT>
): CE<ContentT, ErrorT> {
    return fold(
        onError = map,
        onContent = { this }
    )
}

inline fun <T> CT<T>.flatMapError(
    crossinline map: (Throwable) -> CT<T>
): CT<T> {
    return fold(
        onError = map,
        onContent = { this }
    )
}