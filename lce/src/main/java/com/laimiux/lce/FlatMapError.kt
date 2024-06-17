package com.laimiux.lce

inline fun <L, C, E, NewE> LCE<L, C, E>.flatMapError(
    map: (E) -> LCE<L, C, NewE>
): LCE<L, C, NewE> {
    return foldTypes(
        onError = { map(it.value) },
        onContent = { it },
        onLoading = { it }
    )
}

inline fun <C, E, NewE> UCE<C, E>.flatMapError(
    map: (E) -> UCE<C, NewE>
): UCE<C, NewE> {
    return foldTypes(
        onError = { map(it.value) },
        onContent = { it },
        onLoading = { it }
    )
}

inline fun <C> UCT<C>.flatMapError(
    map: (Throwable) -> UCT<C>
): UCT<C> {
    return foldTypes(
        onError = { map(it.value) },
        onLoading = { it },
        onContent = { it }
    )
}

inline fun <C, E, NewE> CE<C, E>.flatMapError(
    map: (E) -> CE<C, NewE>
): CE<C, NewE> {
    return foldTypes(
        onError = { map(it.value) },
        onContent = { it }
    )
}

inline fun <C> CT<C>.flatMapError(
    map: (Throwable) -> CT<C>
): CT<C> {
    return foldTypes(
        onError = { map(it.value) },
        onContent = { it }
    )
}