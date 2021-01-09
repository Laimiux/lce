package com.laimiux.lce

enum class Merge {
    LOADING_FIRST,
    ERRORS_FIRST,
    FLAT_MAP
}

inline fun <C, C2, T> UCT<C>.merge(
    other: UCT<C2>,
    type: Merge = Merge.ERRORS_FIRST,
    crossinline merge: (C, C2) -> T
): UCT<T> {
    when (type) {
        Merge.LOADING_FIRST -> {
            takeFirstLoading(other) { first, second ->
                first.foldTypes (
                    onError = { it },
                    onContent = { contentOne ->
                        second.foldTypes(
                            onError = { it },
                            onContent = {
                                UCT.content(merge(contentOne.value, it.value))
                            }
                        )
                    }
                )
            }
        }
        Merge.ERRORS_FIRST -> {
            takeFirstError(other) { first, second ->
                first.combine(second).asUCT()
            }
        }
        Merge.FLAT_MAP -> {
            flatMapContent { first ->
                other.flatMapContent { second ->
                    UCT.content(merge(first, second))
                }
            }
        }
    }
}

/**
 * Combines two [UCT] objects into one.
 */
fun <C, C2> UCT<C>.combine(other: UCT<C2>): UCT<Pair<C, C2>> {
    return combine(other) { first, second ->
        Pair(first, second)
    }
}

/**
 * Combines two [UCT] objects into one.
 */
inline fun <C, C2, T> UCT<C>.combine(
    other: UCT<C2>,
    crossinline combine: (C, C2) -> T
): UCT<T> {
    return flatMapContent { first ->
        other.flatMapContent { second ->
            UCT.content(combine(first, second))
        }
    }
}

/**
 * Combines two [UC] objects into one.
 */
fun <C, C2> UC<C>.combine(other: UC<C2>): UC<Pair<C, C2>> {
    return flatMapContent { first ->
        other.flatMapContent { second ->
            UC.content(Pair(first, second))
        }
    }
}


/**
 * Combines two [UC] objects into one.
 */
inline fun <C, C2, T> UC<C>.combine(
    other: UC<C2>,
    crossinline combine: (C, C2) -> T
): UC<T> {
    return flatMapContent { first ->
        other.flatMapContent { second ->
            UC.content(combine(first, second))
        }
    }
}



/**
 * Merging/combining is hard because?
 * - If first event is loading and second event is error, what do we show? error or loading?
 */

/**
 * It combins two [UCT] instances by:
 * Returning first error instance, otherwise
 * Returning first loading instance, otherwise
 * Combining the content
 */
inline fun <C, C2> UCT<C>.combineErrorsFirst(
    other: UCT<C2>,
): UCT<Pair<C, C2>> {

    // Merge priority:
    // Take first error
    // Take first loading
    // Merge content


    return takeFirstError(other) { first, second ->
        first.combine(second).asUCT()
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


/**
 * It will return the current [UCT] if it is an error, otherwise it will
 * call [onOther] with a (Unit/Loading)[UC] type which you can map to another [UCT].
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

/**
 * Takes first error state out of [this] and [other], otherwise calls [onOther].
 */
inline fun <C, C2, T> UCT<C>.takeFirstLoading(
    other: UCT<C2>,
    crossinline onOther: (CT<C>, CT<C2>) -> UCT<T>
): UCT<T> {
    return takeLoadingOrElse { first ->
        other.takeLoadingOrElse { second ->
            onOther(first, second)
        }
    }
}



/**
 * It will return the current [UCT] if it is an error, otherwise it will
 * call [onOther] with a (Unit/Loading)[UC] type which you can map to another [UCT].
 */
inline fun <C, T> UCT<C>.takeLoadingOrElse(
    crossinline onOther: (CT<C>) -> UCT<T>
): UCT<T> {
    return foldTypes(
        onLoading = { it },
        onContent = onOther,
        onError = onOther,
    )
}
