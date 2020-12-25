package com.laimiux.lce

@Suppress("UNCHECKED_CAST")
inline fun <LoadingT, ContentT, ErrorT, NewT> LCE<LoadingT, ContentT, ErrorT>.mapContent(
    crossinline map: (ContentT) -> NewT
): LCE<LoadingT, NewT, ErrorT> {
    return when (val type = asLceType()) {
        is Type.Content -> LCE.content(map(type.value))
        else -> this as LCE<LoadingT, NewT, ErrorT>
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, NewT> UCT<ContentT>.mapContent(
    crossinline map: (ContentT) -> NewT
): UCT<NewT> {
    return when (val type = asLceType()) {
        is Type.Content -> UCT.content(map(type.value))
        else -> this as UCT<NewT>
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, ErrorT, NewT> CE<ContentT, ErrorT>.mapContent(
    crossinline map: (ContentT) -> NewT
): CE<NewT, ErrorT> {
    return when (val type = asLceType()) {
        is Type.Content -> CE.content(map(type.value))
        else -> this as CE<NewT, ErrorT>
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, NewT> CT<ContentT>.mapContent(
    crossinline map: (ContentT) -> NewT
): CT<NewT> {
    return when (val type = asLceType()) {
        is Type.Content -> CT.content(map(type.value))
        else -> this as CT<NewT>
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, NewT> UC<ContentT>.mapContent(
    crossinline map: (ContentT) -> NewT
): UC<NewT> {
    return when (val type = asLceType()) {
        is Type.Content -> UC.content(map(type.value))
        else -> this as UC<NewT>
    }
}