package com.laimiux.lce

@JvmName("flatMapErrorLCE")
inline fun <LoadingT, ContentT, ErrorT, NewErrorT> LCE<LoadingT, ContentT, ErrorT>.flatMapError(
    crossinline map: (ErrorT) -> LCE<LoadingT, ContentT, NewErrorT>
): LCE<LoadingT, ContentT, NewErrorT> {
    return foldTypes(
        onError = { map(it.value) },
        onContent = { it },
        onLoading = { it }
    )
}

inline fun <ContentT, ErrorT, NewErrorT> UCE<ContentT, ErrorT>.flatMapError(
    crossinline map: (ErrorT) -> UCE<ContentT, NewErrorT>
): UCE<ContentT, NewErrorT> {
    return foldTypes(
        onError = { map(it.value) },
        onContent = { it },
        onLoading = { it }
    )
}

inline fun <T> UCT<T>.flatMapError(
    crossinline map: (Throwable) -> UCT<T>
): UCT<T> {
    return foldTypes(
        onError = { map(it.value) },
        onLoading = { it },
        onContent = { it }
    )
}

inline fun <ContentT, ErrorT, NewErrorT> CE<ContentT, ErrorT>.flatMapError(
    crossinline map: (ErrorT) -> CE<ContentT, NewErrorT>
): CE<ContentT, NewErrorT> {
    return foldTypes(
        onError = { map(it.value) },
        onContent = { it }
    )
}

inline fun <T> CT<T>.flatMapError(
    crossinline map: (Throwable) -> CT<T>
): CT<T> {
    return foldTypes(
        onError = { map(it.value) },
        onContent = { it }
    )
}