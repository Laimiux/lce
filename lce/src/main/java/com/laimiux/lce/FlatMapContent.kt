package com.laimiux.lce

/**
 * Maps content value to a new LCE value.
 * ```
 * val event: LCE<String?> = LCE.content(null)
 * val result: LCE<String> = event.flatMapContent { value ->
 *   if (value == null) {
 *     LCE.loading()
 *   } else {
 *     LCE.content(value)
 *   }
 * }
 * ```
 */
inline fun <L, C, E, NewC> LCE<L, C, E>.flatMapContent(
    transform: (C) -> LCE<L, NewC, E>
): LCE<L, NewC, E> {
    return foldTypes(
        onLoading = { it },
        onContent = { transform(it.value) },
        onError = { it }
    )
}

inline fun <C, E, NewC> UCE<C, E>.flatMapContent(
    transform: (C) -> UCE<NewC, E>
): UCE<NewC, E> {
    return foldTypes(
        onLoading = { it },
        onContent = { transform(it.value) },
        onError = { it }
    )
}

inline fun <C, NewC> UCT<C>.flatMapContent(
   transform: (C) -> UCT<NewC>
): UCT<NewC> {
    return foldTypes(
        onLoading = { it },
        onContent = { transform(it.value) },
        onError = { it }
    )
}

inline fun <C, E, NewC> CE<C, E>.flatMapContent(
    transform: (C) -> CE<NewC, E>
): CE<NewC, E> {
    return foldTypes(
        onContent = { transform(it.value) },
        onError = { it }
    )
}

inline fun <C, NewC> CT<C>.flatMapContent(
    transform: (C) -> CT<NewC>
): CT<NewC> {
    return foldTypes(
        onContent = { transform(it.value) },
        onError = { it }
    )
}

inline fun <L, C, NewC> LC<L, C>.flatMapContent(
    transform: (C) -> LC<L, NewC>
): LC<L, NewC> {
    return foldTypes(
        onLoading = { it },
        onContent = { transform(it.value) }
    )
}

inline fun <C, NewC> UC<C>.flatMapContent(
    transform: (C) -> UC<NewC>
): UC<NewC> {
    return foldTypes(
        onLoading = { it },
        onContent = { transform(it.value) }
    )
}

