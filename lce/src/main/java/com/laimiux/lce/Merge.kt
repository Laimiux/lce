package com.laimiux.lce

/**
 * Merges two [UCE] objects into one.
 */
inline fun <C, C2, E, T> UCE<C, E>.merge(
    other: UCE<C2, E>,
    crossinline merge: (C, C2) -> T
): UCE<T, E> {
    return takeFirstError(other) { first, second ->
        first.merge(second, merge).asUCE()
    }
}

/**
 * Merges two [UCE] objects into one.
 */
fun <C, C2, E> UCE<C, E>.merge(other: UCE<C2, E>): UCE<Pair<C, C2>, E> {
    return merge(other) { first, second ->
        Pair(first, second)
    }
}

/**
 * Merges two [UCT] objects into one.
 */
inline fun <C, C2, T> UCT<C>.merge(
    other: UCT<C2>,
    crossinline merge: (C, C2) -> T
): UCT<T> {
    return takeFirstError(other) { first, second ->
        first.merge(second, merge).asUCT()
    }
}

/**
 * Merges two [UCT] objects into one.
 */
fun <C, C2> UCT<C>.merge(other: UCT<C2>): UCT<Pair<C, C2>> {
    return merge(other) { first, second ->
        Pair(first, second)
    }
}

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