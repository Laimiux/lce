package com.laimiux.lce

/**
 * Takes first error state out of [this] or [second], otherwise calls [onOther].
 */
inline fun <C, C2, E, T> UCE<C, E>.takeFirstError(
    second: UCE<C2, E>,
    crossinline onOther: (UC<C>, UC<C2>) -> UCE<T, E>
): UCE<T, E> {
    return takeErrorOrElse { uc ->
        second.takeErrorOrElse { otherUc ->
            onOther(uc, otherUc)
        }
    }
}

/**
 * Takes first error state out of [this], [second] or [third], otherwise calls [onOther].
 */
inline fun <C, C2, C3, E, T> UCE<C, E>.takeFirstError(
    second: UCE<C2, E>,
    third: UCE<C3, E>,
    crossinline onOther: (UC<C>, UC<C2>, UC<C3>) -> UCE<T, E>
): UCE<T, E> {
    return takeErrorOrElse { uc ->
        second.takeErrorOrElse { secondUC ->
            third.takeErrorOrElse { thirdUC ->
                onOther(uc, secondUC, thirdUC)
            }
        }
    }
}

/**
 * Takes first error state out of [this], [second],[third] or [fourth], otherwise calls [onOther].
 */
inline fun <C, C2, C3, C4, E, T> UCE<C, E>.takeFirstError(
    second: UCE<C2, E>,
    third: UCE<C3, E>,
    fourth: UCE<C4, E>,
    crossinline onOther: (UC<C>, UC<C2>, UC<C3>, UC<C4>) -> UCE<T, E>
): UCE<T, E> {
    return takeErrorOrElse { uc ->
        second.takeErrorOrElse { secondUC ->
            third.takeErrorOrElse { thirdUC ->
                fourth.takeErrorOrElse { fourthUC ->
                    onOther(uc, secondUC, thirdUC, fourthUC)
                }
            }
        }
    }
}

/**
 * Takes first error state out of [this] or [second], otherwise calls [onOther].
 */
inline fun <C, C2, T> UCT<C>.takeFirstError(
    second: UCT<C2>,
    crossinline onOther: (UC<C>, UC<C2>) -> UCT<T>
): UCT<T> {
    return takeErrorOrElse { uc ->
        second.takeErrorOrElse { otherUc ->
            onOther(uc, otherUc)
        }
    }
}

/**
 * Takes first error state out of [this], [second] or [third], otherwise calls [onOther].
 */
inline fun <C, C2, C3, T> UCT<C>.takeFirstError(
    second: UCT<C2>,
    third: UCT<C3>,
    crossinline onOther: (UC<C>, UC<C2>, UC<C3>) -> UCT<T>
): UCT<T> {
    return takeErrorOrElse { uc ->
        second.takeErrorOrElse { secondUC ->
            third.takeErrorOrElse { thirdUC ->
                onOther(uc, secondUC, thirdUC)
            }
        }
    }
}

/**
 * Takes first error state out of [this], [second], [third], or [fourth], otherwise calls [onOther].
 */
inline fun <C, C2, C3, C4, T> UCT<C>.takeFirstError(
        second: UCT<C2>,
        third: UCT<C3>,
        fourth: UCT<C4>,
        crossinline onOther: (UC<C>, UC<C2>, UC<C3>, UC<C4>) -> UCT<T>
): UCT<T> {
    return takeErrorOrElse { uc ->
        second.takeErrorOrElse { secondUC ->
            third.takeErrorOrElse { thirdUC ->
                fourth.takeErrorOrElse { fourthUC ->
                    onOther(uc, secondUC, thirdUC, fourthUC)

                }
            }
        }
    }
}
