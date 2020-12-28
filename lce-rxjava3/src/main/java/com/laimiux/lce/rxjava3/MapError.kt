package com.laimiux.lce.rxjava3

import com.laimiux.lce.LCE
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.mapError
import io.reactivex.rxjava3.core.Observable

@JvmName("mapErrorLCE")
inline fun <L, C, E, NewE> Observable<LCE<L, C, E>>.mapError(
    crossinline transform: (E) -> NewE
): Observable<LCE<L, C, NewE>> {
    return map { it.mapError(transform) }
}

@JvmName("mapErrorUCE")
inline fun <C, E, NewE> Observable<UCE<C, E>>.mapError(
    crossinline transform: (E) -> NewE
): Observable<UCE<C, NewE>> {
    return map { it.mapError(transform) }
}

@JvmName("mapErrorUCT")
inline fun <C> Observable<UCT<C>>.mapError(
    crossinline transform: (Throwable) -> Throwable
): Observable<UCT<C>> {
    return map { it.mapError(transform) }
}