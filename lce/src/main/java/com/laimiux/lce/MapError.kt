package com.laimiux.lce

@Suppress("UNCHECKED_CAST")
inline fun <LoadingT, ContentT, ErrorT, NewErrorT> LCE<LoadingT, ContentT, ErrorT>.mapError(
    crossinline map: (ErrorT) -> NewErrorT
): LCE<LoadingT, ContentT, NewErrorT> {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { LCE.error(map(it.value)) }
    )
}

inline fun <ContentT> UCT<ContentT>.mapError(
    crossinline map: (Throwable) -> Throwable
): UCT<ContentT> {
    return foldTypes(
        onLoading = { it },
        onError = { UCT.error(map(it.value)) },
        onContent = { it }
    )
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, ErrorT, NewErrorT> CE<ContentT, ErrorT>.mapError(
    crossinline map: (ErrorT) -> NewErrorT
): CE<ContentT, NewErrorT> {
    return foldTypes(
        onError = { CE.error(map(it.value)) },
        onContent = { it }
    )
}

inline fun <ContentT> CT<ContentT>.mapError(
    crossinline map: (Throwable) -> Throwable
): CT<ContentT> {
    return foldTypes(
        onError = { CT.error(map(it.value)) },
        onContent = { it }
    )
}
