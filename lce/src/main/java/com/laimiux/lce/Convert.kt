package com.laimiux.lce

/**
 * Converts `LCE<Unit, C, Throwable>` to `UCT<C>`
 */
fun <C> LCE<Unit, C, Throwable>.asUCT(): UCT<C> {
    return foldTypes(
        onLoading = { it.asUnitLoading() },
        onError = { it.asThrowableError() },
        onContent = { it }
    )
}

/**
 * Converts `LCE<Unit, C, Throwable>` to `UCT<C>`
 */
fun <C, E> LCE<Unit, C, E>.asUCE(): UCE<C, E> {
    return foldTypes(
        onLoading = { it.asUnitLoading() },
        onError = { it },
        onContent = { it }
    )
}

/**
 * Returns null when current state is loading otherwise returns [CE].
 */
fun <C, E> LCE<Any?, C, E>.asCE(): CE<C, E>? {
    return foldTypes(
        onLoading = { null },
        onContent = { it },
        onError = { it }
    )
}

/**
 * Returns null when current state is loading otherwise returns [CT].
 */
fun <C> LCE<Any?, C, Throwable>.asCT(): CT<C>? {
    return foldTypes(
        onLoading = { null },
        onError = { it.asThrowableError() },
        onContent = { it }
    )
}

/**
 * Returns null on error, otherwise returns [LC].
 */
fun <L, C> LCE<L, C, Any?>.asLC(): LC<L, C>? {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { null }
    )
}

/**
 * Returns null on error, otherwise returns [UC].
 */
fun <C> LCE<Unit, C, Any?>.asUC(): UC<C>? {
    return foldTypes(
        onLoading = { it.asUnitLoading() },
        onContent = { it },
        onError = { null }
    )
}

/**
 * Converts `UCE<C, E>` to `LCE<Unit, C, E>`
 */
fun <C, E> UCE<C, E>.asLCE(): LCE<Unit, C, E> {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { it }
    )
}

/**
 * Returns null when current state is loading otherwise returns [CT].
 */
fun <C> UCE<C, Throwable>.asCT(): CT<C>? {
    return foldTypes(
        onLoading = { null },
        onContent = { it },
        onError = { it.asThrowableError() }
    )
}

/**
 * Returns null when current state is loading otherwise returns [CE].
 */
fun <C, E> UCE<C, E>.asCE(): CE<C, E>? {
    return foldTypes(
        onLoading = { null },
        onContent = { it },
        onError = { it }
    )
}

/**
 * Returns null on error, otherwise returns [LC].
 */
fun <C, E> UCE<C, E>.asLC(): LC<Unit, C>? {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { null }
    )
}

/**
 * Returns null on error, otherwise returns [UC].
 */
fun <C, E> UCE<C, E>.asUC(): UC<C>? {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { null }
    )
}

/**
 * Converts `UCT<C>` to `LCE<Unit, C, Throwable>`
 */
fun <C> UCT<C>.asLCE(): LCE<Unit, C, Throwable> {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { it }
    )
}

/**
 * Converts `UCT<C>` to `UCE<C, Throwable>`
 */
fun <C> UCT<C>.asUCE(): UCE<C, Throwable> {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { it }
    )
}

/**
 * Returns null when current state is loading otherwise returns [CT].
 */
fun <C> UCT<C>.asCT(): CT<C>? {
    return foldTypes(
        onLoading = { null },
        onContent = { it },
        onError = { it }
    )
}

/**
 * Returns null when current state is loading otherwise returns [CE].
 */
fun <C> UCT<C>.asCE(): CE<C, Throwable>? {
    return foldTypes(
        onLoading = { null },
        onContent = { it },
        onError = { it }
    )
}

/**
 * Returns null on error, otherwise returns [LC].
 */
fun <C> UCT<C>.asLC(): LC<Unit, C>? {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { null }
    )
}

/**
 * Returns null on error, otherwise returns [UC].
 */
fun <C> UCT<C>.asUC(): UC<C>? {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { null }
    )
}

/**
 * Converts `CE<C, E>` to `LCE<Nothing, C, E>`
 */
fun <C, E> CE<C, E>.asLCE(): LCE<Nothing, C, E> {
    return foldTypes(
        onContent = { it },
        onError = { it }
    )
}

/**
 * Converts `CE<C, Throwable>` to `UCT<C>`
 */
fun <C, E> CE<C, E>.asUCE(): UCE<C, E> {
    return foldTypes(
        onContent = { it },
        onError = { it }
    )
}

/**
 * Converts `CE<C, Throwable>` to `UCT<C>`
 */
fun <C> CE<C, Throwable>.asUCT(): UCT<C> {
    return foldTypes(
        onContent = { it },
        onError = { it.asThrowableError() }
    )
}

/**
 * Converts `CE<C, Throwable>` to `CT<C>`
 */
fun <C> CE<C, Throwable>.asCT(): CT<C> {
    return foldTypes(
        onContent = { it },
        onError = { it.asThrowableError() }
    )
}

/**
 * Converts `CT<C>` to `LCE<Nothing, C, Throwable>`
 */
fun <C> CT<C>.asLCE(): LCE<Nothing, C, Throwable> {
    return foldTypes(
        onContent = { it },
        onError = { it }
    )
}

/**
 * Converts `CT<C>` to `UCE<C, Throwable>`
 */
fun <C> CT<C>.asUCE(): UCE<C, Throwable> {
    return foldTypes(
        onContent = { it },
        onError = { it }
    )
}

/**
 * Converts `CT<C>` to `UCT<C>`
 */
fun <C> CT<C>.asUCT(): UCT<C> {
    return foldTypes(
        onContent = { it },
        onError = { it }
    )
}

/**
 * Converts `CT<C>` to `CE<C, Throwable>`
 */
fun <C> CT<C>.asCE(): CE<C, Throwable> {
    return foldTypes(
        onContent = { it },
        onError = { it }
    )
}

/**
 * Converts `LC<L, C>` to `LCE<L, C, Nothing>`
 */
fun <L, C> LC<L, C>.asLCE(): LCE<L, C, Nothing> {
    return foldTypes(
        onLoading = { it },
        onContent = { it }
    )
}

/**
 * Converts `LC<Unit, C>` to `UCE<C, Nothing>`
 */
fun <C> LC<Unit, C>.asUCE(): UCE<C, Nothing> {
    return foldTypes(
        onLoading = { it.asUnitLoading() },
        onContent = { it }
    )
}

/**
 * Converts `LC<Unit, C>` to `UCT<C>`
 */
fun <C> LC<Unit, C>.asUCT(): UCT<C> {
    return foldTypes(
        onLoading = { it.asUnitLoading() },
        onContent = { it }
    )
}

/**
 * Converts `LC<Unit, C>` to `UC<C>`
 */
fun <C> LC<Unit, C>.asUC(): UC<C> {
    return foldTypes(
        onLoading = { it.asUnitLoading() },
        onContent = { it }
    )
}

/**
 * Converts `UC<C>` to `LCE<Unit, C, Nothing>`
 */
fun <C> UC<C>.asLCE(): LCE<Unit, C, Nothing> {
    return foldTypes(
        onLoading = { it },
        onContent = { it }
    )
}

/**
 * Converts `UC<C>` to `UCE<C, Nothing>`
 */
fun <C> UC<C>.asUCE(): UCE<C, Nothing> {
    return foldTypes(
        onLoading = { it },
        onContent = { it }
    )
}

/**
 * Converts `UC<C>` to `UCT<C>`
 */
fun <C> UC<C>.asUCT(): UCT<C> {
    return foldTypes(
        onLoading = { it },
        onContent = { it }
    )
}

/**
 * Converts `UC<C>` to `LC<Unit, C>`
 */
fun <C> UC<C>.asLC(): LC<Unit, C> {
    return foldTypes(
        onLoading = { it },
        onContent = { it }
    )
}