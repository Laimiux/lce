package com.laimiux.lce.flow

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.fold
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

@JvmName("doOnEventLCE")
inline fun <L, C, E> Flow<LCE<L, C, E>>.doOnEvent(
    crossinline onLoading: (L) -> Unit = {},
    crossinline onError: (E) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Flow<LCE<L, C, E>> {
    return onEach {
        it.fold(
            onLoading = onLoading,
            onError = onError,
            onContent = onContent
        )
    }
}

@JvmName("doOnEventUCE")
inline fun <C, E> Flow<UCE<C, E>>.doOnEvent(
    crossinline onLoading: () -> Unit = {},
    crossinline onError: (E) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Flow<UCE<C, E>> {
    return onEach {
        it.fold(
            onLoading = onLoading,
            onError = onError,
            onContent = onContent
        )
    }
}

@JvmName("doOnEventUCT")
inline fun <C> Flow<UCT<C>>.doOnEvent(
    crossinline onLoading: () -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Flow<UCT<C>> {
    return onEach {
        it.fold(
            onLoading = onLoading,
            onError = onError,
            onContent = onContent
        )
    }
}

@JvmName("doOnEventCE")
inline fun <C, E> Flow<CE<C, E>>.doOnEvent(
    crossinline onError: (E) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Flow<CE<C, E>> {
    return onEach {
        it.fold(
            onContent = onContent,
            onError = onError
        )
    }
}

@JvmName("doOnEventCT")
inline fun <C> Flow<CT<C>>.doOnEvent(
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Flow<CT<C>> {
    return onEach {
        it.fold(
            onContent = onContent,
            onError = onError
        )
    }
}

@JvmName("doOnEventLC")
inline fun <L, C> Flow<LC<L, C>>.doOnEvent(
    crossinline onLoading: (L) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Flow<LC<L, C>> {
    return onEach {
        it.fold(
            onLoading = onLoading,
            onContent = onContent
        )
    }
}

@JvmName("doOnEventUC")
inline fun <C> Flow<UC<C>>.doOnEvent(
    crossinline onLoading: () -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Flow<UC<C>> {
    return onEach {
        it.fold(
            onLoading = onLoading,
            onContent = onContent
        )
    }
}