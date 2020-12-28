package com.laimiux.lce

inline fun <C, NewC> Type.Content<C>.mapContent(
    crossinline map: (C) -> NewC
): Type.Content<NewC> {
    return Type.Content(map(value))
}

/**
 * Maps the content value of type [C] to another value of type [NewC].
 * ```
 * val result = LCE.content(0).mapContent { "Value is: $it" }
 * ```
 *
 * If you are only mapping value, but not type, the [NewC] can be equal to [C].
 * ```
 * val result = LCE.content(0).mapContent { it + 1 }
 * ```
 */
inline fun <L, C, E, NewC> LCE<L, C, E>.mapContent(
    crossinline map: (C) -> NewC
): LCE<L, NewC, E> {
    return foldTypes(
        onLoading = { it },
        onContent = { LCE.content(map(it.value)) },
        onError = { it }
    )
}

inline fun <C, E, NewC> UCE<C, E>.mapContent(
    crossinline map: (C) -> NewC
): UCE<NewC, E> {
    return foldTypes(
        onLoading = { it },
        onContent = { UCE.content(map(it.value)) },
        onError = { it }
    )
}

inline fun <C, NewC> UCT<C>.mapContent(
    crossinline map: (C) -> NewC
): UCT<NewC> {
    return foldTypes(
        onLoading = { it },
        onContent = { UCT.content(map(it.value)) },
        onError = { it }
    )
}

inline fun <C, E, NewC> CE<C, E>.mapContent(
    crossinline map: (C) -> NewC
): CE<NewC, E> {
    return foldTypes(
        onContent = { CE.content(map(it.value)) },
        onError = { it }
    )
}

inline fun <C, NewC> CT<C>.mapContent(
    crossinline map: (C) -> NewC
): CT<NewC> {
    return foldTypes(
        onContent = { CT.content(map(it.value)) },
        onError = { it }
    )
}

inline fun <C, NewC> UC<C>.mapContent(
    crossinline map: (C) -> NewC
): UC<NewC> {
    return foldTypes(
        onLoading = { it },
        onContent = { UC.content(map(it.value)) }
    )
}