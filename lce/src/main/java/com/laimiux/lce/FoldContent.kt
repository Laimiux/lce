package com.laimiux.lce

inline fun <L, C, E, T> LCE<L, C, E>.foldContent(
    onContent: (C) -> T,
    onOther: (LE<L, E>) -> T
): T {
    return foldTypes(
        onContent = { onContent(it.value) },
        onOther = onOther
    )
}

inline fun <C, E, T> UCE<C, E>.foldContent(
    onContent: (C) -> T,
    onOther: (UE<E>) -> T
): T {
    return foldTypes(
        onContent = { onContent(it.value) },
        onOther = onOther
    )
}

inline fun <C, T> UCT<C>.foldContent(
    onContent: (C) -> T,
    onOther: (UT) -> T
): T {
    return foldTypes(
        onContent = { onContent(it.value) },
        onOther = onOther
    )
}
