package com.laimiux.lce.flow

import com.laimiux.lce.LCE
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.mapError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@JvmName("mapErrorLCE")
inline fun <L, C, E, NewE> Flow<LCE<L, C, E>>.mapError(
    crossinline transform: (E) -> NewE
): Flow<LCE<L, C, NewE>> {
    return map { it.mapError(transform) }
}

@JvmName("mapErrorUCE")
inline fun <C, E, NewE> Flow<UCE<C, E>>.mapError(
    crossinline transform: (E) -> NewE
): Flow<UCE<C, NewE>> {
    return map { it.mapError(transform) }
}

@JvmName("mapErrorUCT")
inline fun <C> Flow<UCT<C>>.mapError(
    crossinline transform: (Throwable) -> Throwable
): Flow<UCT<C>> {
    return map { it.mapError(transform) }
}