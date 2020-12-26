package com.laimiux.lce

@PublishedApi
internal fun Type.Loading<Unit>.asUnitLoading(): Type.Loading.UnitType {
    return Type.Loading.UnitType
}

@PublishedApi
internal fun Type.Error<Throwable>.asThrowableError(): Type.Error.ThrowableType {
    return this as Type.Error.ThrowableType
}