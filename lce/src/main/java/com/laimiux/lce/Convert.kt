package com.laimiux.lce

fun <ContentT> LCE<Unit, ContentT, Throwable>.asUCT(): UCT<ContentT> {
    return when (val type = asLceType()) {
        is Type.Content -> type
        is Type.Error.ThrowableError -> type
        is Type.Loading.Unit -> type
        else -> throw IllegalStateException("this should not happen: $type")
    }
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
        is Type.Error.ThrowableError -> type
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <ContentT> CE<ContentT, Throwable>.asCT(): CT<ContentT> {
    return when(val type = asLceType()) {
        is Type.Content -> type
        is Type.Error.ThrowableError -> type
        else -> throw IllegalStateException("this should not happen: $type")
    }
}