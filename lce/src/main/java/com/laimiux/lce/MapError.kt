package com.laimiux.lce

inline fun <L, C, E, NewE> LCE<L, C, E>.mapError(
    crossinline map: (E) -> NewE
): LCE<L, C, NewE> {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { LCE.error(map(it.value)) }
    )
}

inline fun <C, E, NewE> UCE<C, E>.mapError(
    crossinline map: (E) -> NewE
): UCE<C, NewE> {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { UCE.error(map(it.value)) }
    )
}

inline fun <C> UCT<C>.mapError(
    crossinline map: (Throwable) -> Throwable
): UCT<C> {
    return foldTypes(
        onLoading = { it },
        onError = { UCT.error(map(it.value)) },
        onContent = { it }
    )
}

inline fun <C, E, NewE> CE<C, E>.mapError(
    crossinline map: (E) -> NewE
): CE<C, NewE> {
    return foldTypes(
        onError = { CE.error(map(it.value)) },
        onContent = { it }
    )
}

inline fun <C> CT<C>.mapError(
    crossinline map: (Throwable) -> Throwable
): CT<C> {
    return foldTypes(
        onError = { CT.error(map(it.value)) },
        onContent = { it }
    )
}
