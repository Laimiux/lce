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

inline fun <C : Any, E> Flow<C>.toUCE(
    crossinline mapError: (Throwable) -> E
): Flow<UCE<C, E>> {
    return this
        .map { UCE.content(it) as UCE<C, E> }
        .onStart { UCE.loading() }
        .catch { UCE.error(mapError(it)) }
}

fun <C : Any> Flow<C>.toUCT(): Flow<UCT<C>> {
    return this
        .map { UCT.content(it) as UCT<C> }
        .onStart { UCT.loading() }
        .catch { UCT.error(it) }
}

inline fun <C : Any, E> Flow<C>.toCE(
    crossinline mapError: (Throwable) -> E
): Flow<CE<C, E>> {
    return this
        .map { CE.content(it) as CE<C, E> }
        .catch { CE.error(mapError(it)) }
}

fun <C : Any> Flow<C>.toCT(): Flow<CT<C>> {
    return this
        .map { CT.content(it) as CT<C> }
        .catch { CT.error(it) }
}

fun <C : Any> Flow<C>.toLC(): Flow<LC<Unit, C>> {
    return this
        .map { LC.content(it) as LC<Unit, C> }
        .onStart { LC.loading() }
}

fun <C : Any> Flow<C>.toUC(): Flow<UC<C>> {
    return this
        .map { UC.content(it) as UC<C> }
        .onStart { UC.loading() }
}