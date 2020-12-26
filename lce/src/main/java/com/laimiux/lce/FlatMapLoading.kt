package com.laimiux.lce

inline fun <LoadingT, ContentT, ErrorT, NewLoadingT> LCE<LoadingT, ContentT, ErrorT>.flatMapLoading(
    crossinline map: (LoadingT) -> LCE<NewLoadingT, ContentT, ErrorT>
): LCE<NewLoadingT, ContentT, ErrorT> {
    return foldTypes(
        onLoading = { map(it.value) },
        onContent = { it },
        onError = { it }
    )
}

inline fun <T> UCT<T>.flatMapLoading(
    crossinline map: () -> UCT<T>
): UCT<T> {
    return foldTypes(
        onLoading = { map() },
        onError = { it },
        onContent = { it }
    )
}

inline fun <T> UC<T>.flatMapLoading(
    crossinline map: () -> UC<T>
): UC<T> {
    return foldTypes(
        onLoading = { map() },
        onContent = { it }
    )
}