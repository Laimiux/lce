package com.laimiux.lce.flow

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

@Suppress("USELESS_CAST")
inline fun <C, E> Flow<C>.toUCE(
    crossinline mapError: (Throwable) -> E
): Flow<UCE<C, E>> {
    return this
        .map { UCE.content(it) as UCE<C, E> }
        .onStart { emit(UCE.loading()) }
        .catch { emit(UCE.error(mapError(it))) }
}

fun <C> Flow<C>.toUCT(): Flow<UCT<C>> {
    return this
        .map { UCT.content(it) }
        .onStart { emit(UCT.loading()) }
        .catch { emit(UCT.error(it)) }
}

@Suppress("USELESS_CAST")
inline fun <C, E> Flow<C>.toCE(
    crossinline mapError: (Throwable) -> E
): Flow<CE<C, E>> {
    return this
        .map { CE.content(it) as CE<C, E> }
        .catch { emit(CE.error(mapError(it))) }
}

fun <C> Flow<C>.toCT(): Flow<CT<C>> {
    return this
        .map { CT.content(it) }
        .catch { emit(CT.error(it)) }
}

fun <C> Flow<C>.toLC(): Flow<LC<Unit, C>> {
    return this
        .map { LC.content(it) as LC<Unit, C> }
        .onStart { emit(LC.loading()) }
}

fun <C> Flow<C>.toUC(): Flow<UC<C>> {
    return this
        .map { UC.content(it) }
        .onStart { emit(UC.loading()) }
}