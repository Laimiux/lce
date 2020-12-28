package com.laimiux.lce.rxjava3

import com.laimiux.lce.LCE
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.mapError
import io.reactivex.rxjava3.core.Observable

@JvmName("mapErrorLCE")
inline fun <L, C, E, NewError> Observable<LCE<L, C, E>>.mapError(
    crossinline transform: (E) -> NewError
): Observable<LCE<L, C, NewError>> {
    return map { it.mapError(transform) }
}

@JvmName("mapErrorUCE")
inline fun <C, E, NewError> Observable<UCE<C, E>>.mapError(
    crossinline transform: (E) -> NewError
): Observable<UCE<C, NewError>> {
    return map { it.mapError(transform) }
}

@JvmName("mapErrorUCT")
inline fun <T> Observable<UCT<T>>.mapError(
    crossinline transform: (Throwable) -> Throwable
): Observable<UCT<T>> {
    return map { it.mapError(transform) }
}