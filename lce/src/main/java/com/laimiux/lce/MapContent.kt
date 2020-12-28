package com.laimiux.lce

inline fun <ContentT, NewT> Type.Content<ContentT>.mapContent(
    crossinline map: (ContentT) -> NewT
): Type.Content<NewT> {
    return Type.Content(map(value))
}

inline fun <LoadingT, ContentT, ErrorT, NewT> LCE<LoadingT, ContentT, ErrorT>.mapContent(
    crossinline map: (ContentT) -> NewT
): LCE<LoadingT, NewT, ErrorT> {
    return foldTypes(
        onLoading = { it },
        onContent = { LCE.content(map(it.value)) },
        onError = { it }
    )
}

inline fun <ContentT, ErrorT, NewT> UCE<ContentT, ErrorT>.mapContent(
    crossinline map: (ContentT) -> NewT
): UCE<NewT, ErrorT> {
    return foldTypes(
        onLoading = { it },
        onContent = { UCE.content(map(it.value)) },
        onError = { it }
    )
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, NewT> UCT<ContentT>.mapContent(
    crossinline map: (ContentT) -> NewT
): UCT<NewT> {
    return foldTypes(
        onLoading = { it },
        onContent = { UCT.content(map(it.value)) },
        onError = { it }
    )
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, ErrorT, NewT> CE<ContentT, ErrorT>.mapContent(
    crossinline map: (ContentT) -> NewT
): CE<NewT, ErrorT> {
    return foldTypes(
        onContent = { CE.content(map(it.value)) },
        onError = { it }
    )
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, NewT> CT<ContentT>.mapContent(
    crossinline map: (ContentT) -> NewT
): CT<NewT> {
    return foldTypes(
        onContent = { CT.content(map(it.value)) },
        onError = { it }
    )
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, NewT> UC<ContentT>.mapContent(
    crossinline map: (ContentT) -> NewT
): UC<NewT> {
    return foldTypes(
        onLoading = { it },
        onContent = { UC.content(map(it.value)) }
    )
}