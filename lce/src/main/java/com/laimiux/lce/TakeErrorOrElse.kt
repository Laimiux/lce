package com.laimiux.lce

/**
 * Returns an error [UCT] or calls [onOther] with a loading or content [UC] type.
 */
inline fun <C, T> UCT<C>.takeErrorOrElse(
    crossinline onOther: (UC<C>) -> UCT<T>
): UCT<T> {
    return foldTypes(
        onError = { it },
        onContent = onOther,
        onLoading = onOther
    )
}
