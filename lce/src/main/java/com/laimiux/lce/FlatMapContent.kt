package com.laimiux.lce

@Suppress("UNCHECKED_CAST")
inline fun <LoadingT, ContentT, ErrorT, NewContentT> LCE<LoadingT, ContentT, ErrorT>.flatMapContent(
    crossinline transform: (ContentT) -> LCE<LoadingT, NewContentT, ErrorT>
): LCE<LoadingT, NewContentT, ErrorT> {
    return when (val type = asLceType()) {
        is Type.Content -> transform(type.value)
        else -> this as LCE<LoadingT, NewContentT, ErrorT>
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <F, T> UCT<F>.flatMapContent(transform: (F) -> UCT<T>): UCT<T> {
    return when (val type = asLceType()) {
        is Type.Content -> transform(type.value)
        else -> this as UCT<T>
    }
}

@Suppress("UNCHECKED_CAST")
inline fun <ContentT, ErrorT, NewContentT> CE<ContentT, ErrorT>.flatMapContent(
    crossinline transform: (ContentT) -> CE<NewContentT, ErrorT>
): CE<NewContentT, ErrorT> {
    return fold(
        onContent = transform,
        onError = { this as CE<NewContentT, ErrorT> }
    )
}

@Suppress("UNCHECKED_CAST")
inline fun <F, T> CT<F>.flatMapContent(crossinline transform: (F) -> CT<T>): CT<T> {
    return fold(
        onContent = transform,
        onError = { this as CT<T> }
    )
}

@Suppress("UNCHECKED_CAST")
inline fun <F, T> UC<F>.flatMapContent(
    crossinline transform: (F) -> UC<T>
): UC<T> {
    return fold(
        onContent = transform,
        onLoading = { this as UC<T> }
    )
}