package com.laimiux.lce

/**
 * Merges two [UCE] objects into one.
 */
inline fun <C, C2, E, T> UCE<C, E>.flatMerge(
    other: UCE<C2, E>,
    crossinline merge: (C, C2) -> UCE<T, E>
): UCE<T, E> {
    return takeFirstError(other) { first, second ->
        first.asUCE().flatMapContent { firstContent ->
            second.asUCE().flatMapContent { secondContent ->
                merge(firstContent, secondContent)
            }
        }
    }
}

/**
 * Merges two [UCT] objects into one.
 */
inline fun <C, C2, T> UCT<C>.flatMerge(
    other: UCT<C2>,
    crossinline merge: (C, C2) -> UCT<T>
): UCT<T> {
    return takeFirstError(other) { first, second ->
        first.asUCT().flatMapContent { firstContent ->
            second.asUCT().flatMapContent { secondContent ->
                merge(firstContent, secondContent)
            }
        }
    }
}

/**
 * Merges two [LC] objects into one.
 */
inline fun <L, C, C2, T> LC<L, C>.flatMerge(
    other: LC<L, C2>,
    crossinline merge: (C, C2) -> LC<L, T>
): LC<L, T> {
    return flatMapContent { first ->
        other.flatMapContent { second ->
            merge(first, second)
        }
    }
}

/**
 * Merges two [UC] objects into one.
 */
inline fun <C, C2, T> UC<C>.flatMerge(
    other: UC<C2>,
    crossinline merge: (C, C2) -> UC<T>,
): UC<T> {
    return flatMapContent { first ->
        other.flatMapContent { second ->
            merge(first, second)
        }
    }
}