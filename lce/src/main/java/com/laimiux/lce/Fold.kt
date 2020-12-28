package com.laimiux.lce

inline fun <LoadingT, ContentT, ErrorT, T> LCE<LoadingT, ContentT, ErrorT>.fold(
    crossinline onLoading: (LoadingT) -> T,
    crossinline onError: (ErrorT) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    return foldTypes(
        onLoading = { onLoading(it.value) },
        onContent = { onContent(it.value) },
        onError = { onError(it.value) }
    )
}

inline fun <ContentT, ErrorT, T> UCE<ContentT, ErrorT>.fold(
    crossinline onLoading: () -> T,
    crossinline onError: (ErrorT) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onContent = { onContent(it.value) },
        onError = { onError(it.value) }
    )
}

inline fun <ContentT, T> UCT<ContentT>.fold(
    crossinline onLoading: () -> T,
    crossinline onError: (Throwable) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onContent = { onContent(it.value) } ,
        onError = { onError(it.value) }
    )
}

inline fun <ContentT, ErrorT, T> CE<ContentT, ErrorT>.fold(
    crossinline onError: (ErrorT) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    return foldTypes(
        onContent = { onContent(it.value) },
        onError = { onError(it.value) }
    )
}

inline fun <ContentT, T> CT<ContentT>.fold(
    crossinline onError: (Throwable) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    return foldTypes(
        onContent = { onContent(it.value) },
        onError = { onError(it.value) }
    )
}

inline fun <ContentT, T> UC<ContentT>.fold(
    crossinline onLoading: () -> T,
    crossinline onContent: (ContentT) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onContent = { onContent(it.value) }
    )
}