package com.laimiux.lce

inline fun <L, C, E, NewL> LCE<L, C, E>.flatMapLoading(
    crossinline map: (L) -> LCE<NewL, C, E>
): LCE<NewL, C, E> {
    return foldTypes(
        onLoading = { map(it.value) },
        onContent = { it },
        onError = { it }
    )
}

inline fun <C, E> UCE<C, E>.flatMapLoading(
    crossinline map: () -> UCE<C, E>
): UCE<C, E> {
    return foldTypes(
        onLoading = { map() },
        onContent = { it },
        onError = { it }
    )
}

inline fun <C> UCT<C>.flatMapLoading(
    crossinline map: () -> UCT<C>
): UCT<C> {
    return foldTypes(
        onLoading = { map() },
        onError = { it },
        onContent = { it }
    )
}

inline fun <C> UC<C>.flatMapLoading(
    crossinline map: () -> UC<C>
): UC<C> {
    return foldTypes(
        onLoading = { map() },
        onContent = { it }
    )
}