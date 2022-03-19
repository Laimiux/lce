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
 * Merges three [UCE] objects into one.
 */
inline fun <C, C2, C3, E, T> UCE<C, E>.merge(
    second: UCE<C2, E>,
    third: UCE<C3, E>,
    crossinline merge: (C, C2, C3) -> T
): UCE<T, E> {
    return takeFirstError(second, third) { firstUC, secondUC, thirdUC ->
        firstUC.merge(secondUC, thirdUC, merge).asUCE()
    }
}

/**
 * Merges three [UCE] objects into one.
 */
inline fun <C, C2, C3, C4, E, T> UCE<C, E>.merge(
    second: UCE<C2, E>,
    third: UCE<C3, E>,
    fourth: UCE<C4, E>,
    crossinline merge: (C, C2, C3, C4) -> T
): UCE<T, E> {
    return takeFirstError(second, third, fourth) { firstUC, secondUC, thirdUC, fourthUC ->
        firstUC.merge(secondUC, thirdUC, fourthUC, merge).asUCE()
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
    second: UCT<C2>,
    crossinline merge: (C, C2) -> T
): UCT<T> {
    return takeFirstError(second) { firstUC, secondUC ->
        firstUC.merge(secondUC, merge).asUCT()
    }
}

/**
 * Merges three [UCT] objects into one.
 */
inline fun <C, C2, C3, T> UCT<C>.merge(
    second: UCT<C2>,
    third: UCT<C3>,
    crossinline merge: (C, C2, C3) -> T
): UCT<T> {
    return takeFirstError(second, third) { firstUC, secondUC, thirdUC ->
        firstUC.merge(secondUC, thirdUC, merge).asUCT()
    }
}

/**
 * Merges four [UCT] objects into one.
 */
inline fun <C, C2, C3, C4, T> UCT<C>.merge(
    second: UCT<C2>,
    third: UCT<C3>,
    fourth: UCT<C4>,
    crossinline merge: (C, C2, C3, C4) -> T
): UCT<T> {
    return takeFirstError(second, third, fourth) { firstUC, secondUC, thirdUC, fourthUC ->
        firstUC.merge(secondUC, thirdUC, fourthUC, merge).asUCT()
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
 * Merges three [LC] objects into one.
 */
inline fun <L, C, C2, C3, T> LC<L, C>.merge(
    second: LC<L, C2>,
    third: LC<L, C3>,
    crossinline merge: (C, C2, C3) -> T
): LC<L, T> {
    return flatMapContent { firstContent ->
        second.flatMapContent { secondContent ->
            third.flatMapContent { thirdContent ->
                LC.content(merge(firstContent, secondContent, thirdContent))
            }
        }
    }
}

/**
 * Merges three [LC] objects into one.
 */
inline fun <L, C, C2, C3, C4, T> LC<L, C>.merge(
    second: LC<L, C2>,
    third: LC<L, C3>,
    fourth: LC<L, C4>,
    crossinline merge: (C, C2, C3, C4) -> T
): LC<L, T> {
    return flatMapContent { firstContent ->
        second.flatMapContent { secondContent ->
            third.flatMapContent { thirdContent ->
                fourth.flatMapContent { fourthContent ->
                    LC.content(merge(firstContent, secondContent, thirdContent, fourthContent))
                }
            }
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

/**
 * Merges three [UC] objects into one.
 */
inline fun <C, C2, C3, T> UC<C>.merge(
    second: UC<C2>,
    third: UC<C3>,
    crossinline merge: (C, C2, C3) -> T
): UC<T> {
    return flatMapContent { firstContent ->
        second.flatMapContent { secondContent ->
            third.flatMapContent { thirdContent ->
                UC.content(merge(firstContent, secondContent, thirdContent))
            }
        }
    }
}

/**
 * Merges four [UC] objects into one.
 */
inline fun <C, C2, C3, C4, T> UC<C>.merge(
    second: UC<C2>,
    third: UC<C3>,
    fourth: UC<C4>,
    crossinline merge: (C, C2, C3, C4) -> T
): UC<T> {
    return flatMapContent { firstContent ->
        second.flatMapContent { secondContent ->
            third.flatMapContent { thirdContent ->
                fourth.flatMapContent { fourthContent ->
                    UC.content(merge(firstContent, secondContent, thirdContent, fourthContent))
                }
            }
        }
    }
}