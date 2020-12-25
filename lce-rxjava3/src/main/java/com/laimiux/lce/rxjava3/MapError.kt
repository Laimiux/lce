package com.laimiux.lce.rxjava3

import com.laimiux.lce.UCT
import com.laimiux.lce.mapError
import io.reactivex.rxjava3.core.Observable


@JvmName("mapErrorUCT")
inline fun <T> Observable<UCT<T>>.mapError(
    crossinline transform: (Throwable) -> Throwable
): Observable<UCT<T>> {
    return map { it.mapError(transform) }
}