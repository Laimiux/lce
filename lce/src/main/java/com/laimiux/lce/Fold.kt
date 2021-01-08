package com.laimiux.lce

/**
 * Converts LCE into a value of type [T] by mapping each possible
 * variant [onLoading], [onError] and [onContent].
 */
inline fun <L, C, E, T> LCE<L, C, E>.fold(
    crossinline onLoading: (L) -> T,
    crossinline onError: (E) -> T,
    crossinline onContent: (C) -> T
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
    crossinline onLoading: () -> T,
    crossinline onError: (E) -> T,
    crossinline onContent: (C) -> T
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
    crossinline onLoading: () -> T,
    crossinline onError: (Throwable) -> T,
    crossinline onContent: (C) -> T
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
    crossinline onError: (E) -> T,
    crossinline onContent: (C) -> T
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
    crossinline onError: (Throwable) -> T,
    crossinline onContent: (C) -> T
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
    crossinline onLoading: (L) -> T,
    crossinline onContent: (C) -> T
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
    crossinline onLoading: () -> T,
    crossinline onContent: (C) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onContent = { onContent(it.value) }
    )
}