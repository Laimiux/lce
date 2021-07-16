package com.laimiux.lce

/**
 * Returns [CE] if is a content or an error event, otherwise returns null.
 */
fun <C, E> LCE<Any?, C, E>.takeIfCE(): CE<C, E>? {
    return asCE()
}

/**
 * Returns [CT] if is a content or an error event, otherwise returns null.
 */
fun <C> LCE<Any?, C, Throwable>.takeIfCT(): CT<C>? {
    return asCT()
}

/**
 * Returns [CE] if is a content or an error event, otherwise returns null.
 */
fun <C, E> UCE<C, E>.takeIfCE(): CE<C, E>? {
    return asLCE().asCE()
}

/**
 * Returns [CT] if is a content or an error event, otherwise returns null.
 */
fun <C> UCE<C, Throwable>.takeIfCT(): CT<C>? {
    return asLCE().asCT()
}

/**
 * Returns null if this is loading event and it doesn't match the predicate.
 */
inline fun <L, C, E> LCE<L, C, E>.takeIfLoading(
    crossinline predicate: (L) -> Boolean
): LCE<L, C, E>? {
    return foldTypes(
        onLoading = { loading -> loading.takeIf { predicate(it.value) } },
        onOther = { this }
    )
}