package com.laimiux.lce

/**
 * Converts LCE into a value of type [T] by mapping each possible
 * variant [onLoading], [onError] and [onContent].
 */
inline fun <L, C, E, T> LCE<L, C, E>.fold(
    onLoading: (L) -> T,
    onError: (E) -> T,
    onContent: (C) -> T
): T {
    return foldTypes(
        onLoading = { onLoading(it.value) },
        onContent = { onContent(it.value) },
        onError = { onError(it.value) }
    )
}

/**
 * Converts UCE into a value of type [T] by mapping each possible
 * variant [onLoading], [onError] and [onContent].
 */
inline fun <C, E, T> UCE<C, E>.fold(
    onLoading: () -> T,
    onError: (E) -> T,
    onContent: (C) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onContent = { onContent(it.value) },
        onError = { onError(it.value) }
    )
}

/**
 * Converts UCT into a value of type [T] by mapping each possible
 * variant [onLoading], [onError] and [onContent].
 */
inline fun <C, T> UCT<C>.fold(
    onLoading: () -> T,
    onError: (Throwable) -> T,
    onContent: (C) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onContent = { onContent(it.value) } ,
        onError = { onError(it.value) }
    )
}

/**
 * Converts CE into a value of type [T] by mapping each possible
 * variant [onError] and [onContent].
 */
inline fun <C, E, T> CE<C, E>.fold(
    onError: (E) -> T,
    onContent: (C) -> T
): T {
    return foldTypes(
        onContent = { onContent(it.value) },
        onError = { onError(it.value) }
    )
}

/**
 * Converts CT into a value of type [T] by mapping each possible
 * variant [onError] and [onContent].
 */
inline fun <C, T> CT<C>.fold(
    onError: (Throwable) -> T,
    onContent: (C) -> T
): T {
    return foldTypes(
        onContent = { onContent(it.value) },
        onError = { onError(it.value) }
    )
}

/**
 * Converts LC into a value of type [T] by mapping each possible
 * variant [onLoading] and [onContent].
 */
inline fun <L, C, T> LC<L, C>.fold(
    onLoading: (L) -> T,
    onContent: (C) -> T
): T {
    return foldTypes(
        onLoading = { onLoading(it.value) },
        onContent = { onContent(it.value) }
    )
}

/**
 * Converts UC into a value of type [T] by mapping each possible
 * variant [onLoading] and [onContent].
 */
inline fun <C, T> UC<C>.fold(
    onLoading: () -> T,
    onContent: (C) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onContent = { onContent(it.value) }
    )
}