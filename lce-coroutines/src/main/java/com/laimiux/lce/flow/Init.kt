package com.laimiux.lce.flow

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.asUCT
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

/**
 * Runs the operation and returns a [CT] result.
 */
@Suppress("TooGenericExceptionCaught")
suspend inline fun <Result> runCT(execute: suspend () -> Result): CT<Result> {
    return try {
        CT.content(execute())
    } catch (e: CancellationException) {
        // We need to emit cancellation errors
        throw e
    } catch (e: Throwable) {
        CT.error(e)
    }
}

/**
 * Creates a flow that executes [operation] and emits a [UCT] for the operation [Result].
 */
inline fun <Result> uctFlow(crossinline operation: suspend () -> Result): Flow<UCT<Result>> {
    return flow {
        emit(UCT.loading())

        val result = runCT(operation)
        emit(result.asUCT())
    }
}

@Suppress("USELESS_CAST")
inline fun <C, E> Flow<C>.toUCE(
    crossinline mapError: (Throwable) -> E
): Flow<UCE<C, E>> {
    return this
        .map { UCE.content(it) as UCE<C, E> }
        .onStart { emit(UCE.loading()) }
        .catchNonCancellation { emit(UCE.error(mapError(it))) }
}

fun <C> Flow<C>.toUCT(): Flow<UCT<C>> {
    return this
        .map { UCT.content(it) }
        .onStart { emit(UCT.loading()) }
        .catchNonCancellation { emit(UCT.error(it)) }
}

@Suppress("USELESS_CAST")
inline fun <C, E> Flow<C>.toCE(
    crossinline mapError: (Throwable) -> E
): Flow<CE<C, E>> {
    return this
        .map { CE.content(it) as CE<C, E> }
        .catchNonCancellation { emit(CE.error(mapError(it))) }
}

fun <C> Flow<C>.toCT(): Flow<CT<C>> {
    return this
        .map { CT.content(it) }
        .catchNonCancellation { emit(CT.error(it)) }
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

@PublishedApi
internal fun <T> Flow<T>.catchNonCancellation(
    onError: suspend FlowCollector<T>.(Throwable) -> Unit
): Flow<T> {
    return catch {
        if (it is CancellationException) {
            throw it
        }
        onError(it)
    }
}