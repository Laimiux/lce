package com.laimiux.lce

fun <ContentT> LCE<Unit, ContentT, Throwable>.asUCT(): UCT<ContentT> {
    return foldTypes(
        onLoading = { it.asUnitLoading() },
        onError = { it.asThrowableError() },
        onContent = { it }
    )
}

fun <C> UCT<C>.asLCE(): LCE<Unit, C, Throwable> {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { it }
    )
}

fun <ContentT, ErrorT> CE<ContentT, ErrorT>.asLCE(): LCE<Nothing, ContentT, ErrorT> {
    return foldTypes(
        onContent = { it },
        onError = { it }
    )
}

fun <ContentT> CE<ContentT, Throwable>.asUCT(): UCT<ContentT> {
    return foldTypes(
        onContent = { it },
        onError = { it.asThrowableError() }
    )
}

fun <ContentT> CE<ContentT, Throwable>.asCT(): CT<ContentT> {
    return foldTypes(
        onContent = { it },
        onError = { it.asThrowableError() }
    )
}

fun <ContentT> CT<ContentT>.asCE(): CE<ContentT, Throwable> {
    return foldTypes(
        onContent = { it },
        onError = { it }
    )
}

fun <ContentT> CT<ContentT>.asUCT(): UCT<ContentT> {
    return foldTypes(
        onContent = { it },
        onError = { it }
    )
}

fun <ContentT> CT<ContentT>.asLCE(): LCE<Nothing, ContentT, Throwable> {
    return foldTypes(
        onContent = { it },
        onError = { it }
    )
}

fun <ContentT> UC<ContentT>.asLCE(): LCE<Unit, ContentT, Nothing> {
    return foldTypes(
        onLoading = { it },
        onContent = { it }
    )
}

fun <ContentT> UC<ContentT>.asUCT(): UCT<ContentT> {
    return foldTypes(
        onLoading = { it },
        onContent = { it }
    )
}

internal fun Type.Loading<Unit>.asUnitLoading(): Type.Loading.UnitType {
    return Type.Loading.UnitType
}

internal fun Type.Error<Throwable>.asThrowableError(): Type.Error.ThrowableType {
    return this as Type.Error.ThrowableType
}