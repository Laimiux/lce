package com.laimiux.lce

inline fun <LoadingT, ContentT, ErrorT, T> LCE<LoadingT, ContentT, ErrorT>.fold(
    crossinline onLoading: (LoadingT) -> T,
    crossinline onError: (ErrorT) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    return asLceType().fold(
        onLoading = onLoading,
        onError = onError,
        onContent = onContent
    )
}

inline fun <ContentT, T> UCT<ContentT>.fold(
    crossinline onLoading: () -> T,
    crossinline onError: (Throwable) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    val type = this.asLceType()
    return type.fold(
        onLoading = { onLoading() },
        onError = onError,
        onContent = onContent
    )
}

inline fun <ContentT, ErrorT, T> CE<ContentT, ErrorT>.fold(
    crossinline onError: (ErrorT) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    val type = asLceType()
    return type.fold(
        onLoading = { throw IllegalStateException("should not happen: $this") },
        onError = onError,
        onContent = onContent
    )
}

inline fun <ContentT, T> CT<ContentT>.fold(
    crossinline onError: (Throwable) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    val type = asLceType()
    return type.fold(
        onLoading = Utils.NOT_REACHABLE,
        onError = onError,
        onContent = onContent
    )
}

inline fun <ContentT, T> UC<ContentT>.fold(
    crossinline onLoading: (Any?) -> T,
    crossinline onContent: (ContentT) -> T
): T {
    return asLceType().fold(
        onLoading = onLoading,
        onError = Utils.NOT_REACHABLE,
        onContent = onContent
    )
}