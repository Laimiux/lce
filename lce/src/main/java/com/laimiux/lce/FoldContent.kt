package com.laimiux.lce

inline fun <L, C, E, T> LCE<L, C, E>.foldContent(
    crossinline onContent: (C) -> T,
    crossinline onOther: () -> T
): T {
    return foldTypes(
        onContent = { onContent(it.value) },
        onOther = onOther
    )
}

inline fun <C, E, T> UCE<C, E>.foldContent(
    crossinline onContent: (C) -> T,
    crossinline onOther: () -> T
): T {
    return foldTypes(
        onContent = { onContent(it.value) },
        onOther = onOther
    )
}

inline fun <C, T> UCT<C>.foldContent(
    crossinline onContent: (C) -> T,
    crossinline onOther: () -> T
): T {
    return foldTypes(
        onContent = { onContent(it.value) },
        onOther = onOther
    )
}
