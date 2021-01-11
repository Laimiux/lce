package com.laimiux.lce

inline fun <L, C, E, T> LCE<L, C, E>.foldError(
    crossinline onError: (E) -> T,
    crossinline onOther: (LC<L, C>) -> T
): T {
    return foldTypes(
        onError = { onError(it.value) },
        onOther = onOther
    )
}

inline fun <C, E, T> UCE<C, E>.foldError(
    crossinline onError: (E) -> T,
    crossinline onOther: (UC<C>) -> T
): T {
    return foldTypes(
        onError = { onError(it.value) },
        onOther = onOther
    )
}

inline fun <C, T> UCT<C>.foldError(
    crossinline onError: (Throwable) -> T,
    crossinline onOther: (UC<C>) -> T
): T {
    return foldTypes(
        onError = { onError(it.value) },
        onOther = onOther
    )
}
