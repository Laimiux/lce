package com.laimiux.lce

inline fun <LoadingT, ContentT, ErrorT, NewContentT> LCE<LoadingT, ContentT, ErrorT>.flatMapContent(
    crossinline transform: (ContentT) -> LCE<LoadingT, NewContentT, ErrorT>
): LCE<LoadingT, NewContentT, ErrorT> {
    return foldTypes(
        onLoading = { it },
        onContent = { transform(it.value) },
        onError = { it }
    )
}

inline fun <F, T> UCT<F>.flatMapContent(
   crossinline transform: (F) -> UCT<T>
): UCT<T> {
    return foldTypes(
        onLoading = { it },
        onContent = { transform(it.value) },
        onError = { it }
    )
}

inline fun <ContentT, ErrorT, NewContentT> CE<ContentT, ErrorT>.flatMapContent(
    crossinline transform: (ContentT) -> CE<NewContentT, ErrorT>
): CE<NewContentT, ErrorT> {
    return foldTypes(
        onContent = { transform(it.value) },
        onError = { it }
    )
}

inline fun <F, T> CT<F>.flatMapContent(
    crossinline transform: (F) -> CT<T>
): CT<T> {
    return foldTypes(
        onContent = { transform(it.value) },
        onError = { it }
    )
}

inline fun <F, T> UC<F>.flatMapContent(
    crossinline transform: (F) -> UC<T>
): UC<T> {
    return foldTypes(
        onLoading = { it },
        onContent = { transform(it.value) }
    )
}

