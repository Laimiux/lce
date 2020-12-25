package com.laimiux.lce

fun Type.Loading<Unit>.asUnitLoading(): Type.Loading.UnitType {
    return Type.Loading.UnitType
}

fun Type.Error<Throwable>.asThrowableError(): Type.Error.ThrowableType {
    return this as Type.Error.ThrowableType
}

fun <ContentT> LCE<Unit, ContentT, Throwable>.asUCT(): UCT<ContentT> {
    return foldTypes(
        onLoading = { it.asUnitLoading() },
        onError = { it.asThrowableError() },
        onContent = { it }
    )
}

inline fun <C> UCT<C>.asLCE(): LCE<Unit, C, Throwable> {
    return foldTypes(
        onLoading = { it },
        onContent = { it },
        onError = { it }
    )
}

inline fun <ContentT> CE<ContentT, Throwable>.asUCT(): UCT<ContentT> {
    return when(val type = asLceType()) {
        is Type.Content -> type
        is Type.Error.ThrowableType -> type
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <ContentT> CE<ContentT, Throwable>.asCT(): CT<ContentT> {
    return when(val type = asLceType()) {
        is Type.Content -> type
        is Type.Error.ThrowableType -> type
        else -> throw IllegalStateException("this should not happen: $type")
    }
}