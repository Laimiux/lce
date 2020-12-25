package com.laimiux.lce

@Suppress("UNCHECKED_CAST")
inline fun <LoadingT, ContentT, ErrorT, NewErrorT> LCE<LoadingT, ContentT, ErrorT>.mapError(
    crossinline map: (ErrorT) -> NewErrorT
): LCE<LoadingT, ContentT, NewErrorT> {
    return when (val type = asLceType()) {
        is Type.Error -> CE.error(map(type.value))
        is Type.ThrowableError -> CE.error(map(type.value as ErrorT))
        else -> this as LCE<LoadingT, ContentT, NewErrorT>
    }
}

inline fun <ContentT> UCT<ContentT>.mapError(
    crossinline map: (Throwable) -> Throwable
): UCT<ContentT> {
    return when (val type = asLceType()) {
        is Type.ThrowableError -> UCT.error(map(type.value))
        is Type.Error -> UCT.error(map(type.value))
        else -> this
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, ErrorT, NewErrorT> CE<ContentT, ErrorT>.mapError(
    crossinline map: (ErrorT) -> NewErrorT
): CE<ContentT, NewErrorT> {
    return when (val type = asLceType()) {
        is Type.Error -> CE.error(map(type.value))
        is Type.ThrowableError -> CE.error(map(type.value as ErrorT))
        else -> this as CE<ContentT, NewErrorT>
    }
}

inline fun <ContentT> CT<ContentT>.mapError(
    crossinline map: (Throwable) -> Throwable
): CT<ContentT> {
    return when (val type = asLceType()) {
        is Type.ThrowableError -> UCT.error(map(type.value))
        is Type.Error -> UCT.error(map(type.value))
        else -> this
    }
}