package com.laimiux.lce

inline fun <L, C, E, T> LCE<L, C, E>.foldLoading(
    onLoading: (L) -> T,
    onOther: (CE<C, E>) -> T
): T {
    return foldTypes(
        onLoading = { onLoading(it.value) },
        onOther = onOther
    )
}

inline fun <C, E, T> UCE<C, E>.foldLoading(
    onLoading: () -> T,
    onOther: (CE<C, E>) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onOther = onOther
    )
}

inline fun <C, T> UCT<C>.foldLoading(
    onLoading: () -> T,
    onOther: (CT<C>) -> T
): T {
    return foldTypes(
        onLoading = { onLoading() },
        onOther = onOther
    )
}