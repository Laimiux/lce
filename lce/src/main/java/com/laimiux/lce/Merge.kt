package com.laimiux.lce


/**
 * Merges two [UC] objects into one.
 */
fun <C, C2> UC<C>.merge(other: UC<C2>): UC<Pair<C, C2>> {
    return merge(other) { first, second -> Pair(first, second) }
}

/**
 * Merges two [UC] objects into one.
 */
inline fun <C, C2, T> UC<C>.merge(
    other: UC<C2>,
    crossinline merge: (C, C2) -> T
): UC<T> {
    return flatMapContent { first ->
        other.flatMapContent { second ->
            UC.content(merge(first, second))
        }
    }
}