package com.laimiux.lce

/**
 * Takes first error state out of [this] and [other], otherwise calls [onOther].
 */
inline fun <C, C2, E, T> UCE<C, E>.takeFirstError(
    other: UCE<C2, E>,
    crossinline onOther: (UC<C>, UC<C2>) -> UCE<T, E>
): UCE<T, E> {
    return takeErrorOrElse { uc ->
        other.takeErrorOrElse { otherUc ->
            onOther(uc, otherUc)
        }
    }
}

/**
 * Takes first error state out of [this] and [other], otherwise calls [onOther].
 */
inline fun <C, C2, T> UCT<C>.takeFirstError(
    other: UCT<C2>,
    crossinline onOther: (UC<C>, UC<C2>) -> UCT<T>
): UCT<T> {
    return takeErrorOrElse { uc ->
        other.takeErrorOrElse { otherUc ->
            onOther(uc, otherUc)
        }
    }
}

