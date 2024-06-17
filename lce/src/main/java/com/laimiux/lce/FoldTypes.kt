package com.laimiux.lce

inline fun <L, C, E, T> LCE<L, C, E>.foldTypes(
    onLoading: (Type.Loading<L>) -> T,
    onContent: (Type.Content<C>) -> T,
    onError: (Type.Error<E>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading -> onLoading(type)
        is Type.Content -> onContent(type)
        is Type.Error -> onError(type)
    }
}

@JvmName("foldLoadingType")
inline fun <L, C, E, T> LCE<L, C, E>.foldTypes(
    onLoading: (Type.Loading<L>) -> T,
    onOther: (CE<C, E>) -> T
): T {
    return foldTypes(
        onLoading = onLoading,
        onContent = onOther,
        onError = onOther
    )
}

@JvmName("foldContentType")
inline fun <L, C, E, T> LCE<L, C, E>.foldTypes(
    onContent: (Type.Content<C>) -> T,
    onOther: (LE<L, E>) -> T
): T {
    return foldTypes(
        onLoading = onOther,
        onContent = onContent,
        onError = onOther,
    )
}

@JvmName("foldErrorType")
inline fun <L, C, E, T> LCE<L, C, E>.foldTypes(
    onError: (Type.Error<E>) -> T,
    onOther: (LC<L, C>) -> T
): T {
    return foldTypes(
        onLoading = onOther,
        onContent = onOther,
        onError = onError
    )
}

inline fun <C, E, T> UCE<C, E>.foldTypes(
    onLoading: (Type.Loading.UnitType) -> T,
    onContent: (Type.Content<C>) -> T,
    onError: (Type.Error<E>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Content -> onContent(type)
        is Type.Error -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

@JvmName("foldLoadingType")
inline fun <C, E, T> UCE<C, E>.foldTypes(
    onLoading: (Type.Loading.UnitType) -> T,
    onOther: (CE<C, E>) -> T
): T {
    return foldTypes(
        onLoading = onLoading,
        onContent = onOther,
        onError = onOther
    )
}

@JvmName("foldContentType")
inline fun <C, E, T> UCE<C, E>.foldTypes(
    onContent: (Type.Content<C>) -> T,
    onOther: (UE<E>) -> T
): T {
    return foldTypes(
        onLoading = onOther,
        onContent = onContent,
        onError = onOther,
    )
}

@JvmName("foldErrorType")
inline fun <C, E, T> UCE<C, E>.foldTypes(
    onError: (Type.Error<E>) -> T,
    onOther: (UC<C>) -> T
): T {
    return foldTypes(
        onLoading = onOther,
        onContent = onOther,
        onError = onError
    )
}

inline fun <C, T> UCT<C>.foldTypes(
    onLoading: (Type.Loading.UnitType) -> T,
    onContent: (Type.Content<C>) -> T,
    onError: (Type.Error.ThrowableType) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Content -> onContent(type)
        is Type.Error.ThrowableType -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

@JvmName("foldLoadingType")
inline fun <C, T> UCT<C>.foldTypes(
    onLoading: (Type.Loading.UnitType) -> T,
    onOther: (CT<C>) -> T
): T {
    return foldTypes(
        onLoading = onLoading,
        onContent = onOther,
        onError = onOther
    )
}

@JvmName("foldContentType")
inline fun <C, T> UCT<C>.foldTypes(
    onContent: (Type.Content<C>) -> T,
    onOther: (UT) -> T
): T {
    return foldTypes(
        onLoading = onOther,
        onContent = onContent,
        onError = onOther,
    )
}

@JvmName("foldErrorType")
inline fun <C, T> UCT<C>.foldTypes(
    onError: (Type.Error.ThrowableType) -> T,
    onOther: (UC<C>) -> T
): T {
    return foldTypes(
        onLoading = onOther,
        onContent = onOther,
        onError = onError
    )
}

inline fun <C, E, T> CE<C, E>.foldTypes(
    onContent: (Type.Content<C>) -> T,
    onError: (Type.Error<E>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Content -> onContent(type)
        is Type.Error -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <C, T> CT<C>.foldTypes(
    onContent: (Type.Content<C>) -> T,
    onError: (Type.Error.ThrowableType) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Content -> onContent(type)
        is Type.Error.ThrowableType -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <L, C, T> LC<L, C>.foldTypes(
    onLoading: (Type.Loading<L>) -> T,
    onContent: (Type.Content<C>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading -> onLoading(type)
        is Type.Content -> onContent(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <C, T> UC<C>.foldTypes(
    onLoading: (Type.Loading.UnitType) -> T,
    onContent: (Type.Content<C>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Content -> onContent(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <L, E, T> LE<L, E>.foldTypes(
    onLoading: (Type.Loading<L>) -> T,
    onError: (Type.Error<E>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading -> onLoading(type)
        is Type.Error -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <L, T> LT<L>.foldTypes(
    onLoading: (Type.Loading<L>) -> T,
    onError: (Type.Error.ThrowableType) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading -> onLoading(type)
        is Type.Error.ThrowableType -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <E, T> UE<E>.foldTypes(
    onLoading: (Type.Loading.UnitType) -> T,
    onError: (Type.Error<E>) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Error -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}

inline fun <T> UT.foldTypes(
    onLoading: (Type.Loading.UnitType) -> T,
    onError: (Type.Error.ThrowableType) -> T
): T {
    return when (val type = asLceType()) {
        is Type.Loading.UnitType -> onLoading(type)
        is Type.Error.ThrowableType -> onError(type)
        else -> throw IllegalStateException("this should not happen: $type")
    }
}