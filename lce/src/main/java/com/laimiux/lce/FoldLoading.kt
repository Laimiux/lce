package com.laimiux.lce

inline fun <L, C, E, T> LCE<L, C, E>.foldLoading(
    crossinline onLoading: (L) -> T,
    crossinline onOther: (CE<C, E>) -> T
): T {
    return foldTypes(
        onLoading = { onLoading(it.value) },
        onOther = onOther
    )
}

inline fun <C, E, T> UCE<C, E>.foldLoading(
    crossinline onLoading: () -> T,
    crossinline onOther: (CE<C, E>) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onOther = onOther
    )
}

inline fun <C, T> UCT<C>.foldLoading(
    crossinline onLoading: () -> T,
    crossinline onOther: (CT<C>) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onOther = onOther
    )
}