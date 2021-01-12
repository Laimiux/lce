package com.laimiux.lce


/**
 * Merges two [LC] objects into one.
 */
fun <L, C, C2> LC<L, C>.merge(other: LC<L, C2>): LC<L, Pair<C, C2>> {
    return merge(other) { first, second -> Pair(first, second) }
}

/**
 * Merges two [LC] objects into one.
 */
inline fun <L, C, C2, T> LC<L, C>.merge(
    other: LC<L, C2>,
    crossinline merge: (C, C2) -> T
): LC<L, T> {
    return flatMapContent { first ->
        other.flatMapContent { second ->
            LC.content(merge(first, second))
        }
    }
}


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