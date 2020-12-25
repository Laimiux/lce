package com.laimiux.lce

inline fun <LoadingT, ContentT, ErrorT> LCE<LoadingT, ContentT, ErrorT>.flatMapLoading(
    crossinline map: (LoadingT) -> LCE<LoadingT, ContentT, ErrorT>
): LCE<LoadingT, ContentT, ErrorT> {
    return fold(
        onLoading = map,
        onContent = { this },
        onError = { this }
    )
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

inline fun <T> UC<T>.flatMapLoading(
    crossinline map: () -> UC<T>
): UC<T> {
    return fold(
        onLoading = { map() },
        onContent = { this }
    )
}