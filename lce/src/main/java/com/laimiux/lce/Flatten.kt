package com.laimiux.lce


fun <L, C, E> LC<L, CE<C, E>>.flatten(): LCE<L, C, E> {
    return foldTypes(
        onLoading = { it },
        onContent = { it.value.asLCE() }
    )
}

@JvmName("flattenLCT")
fun <L, C> LC<L, CT<C>>.flatten(): LCE<L, C, Throwable> {
    return foldTypes(
        onLoading = { it },
        onContent = { it.value.asLCE() }
    )
}

fun <C, E> UC<CE<C, E>>.flatten(): UCE<C, E> {
    return foldTypes(
        onLoading = { it },
        onContent = { it.value.asUCE() }
    )
}

fun <C> UC<CT<C>>.flatten(): UCT<C> {
    return foldTypes(
        onLoading = { it },
        onContent = { it.value.asUCT() }
    )
}