package com.laimiux.lce.rxjava3

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.fold
import io.reactivex.rxjava3.core.Observable

@JvmName("doOnEventLCE")
inline fun <L, C, E> Observable<LCE<L, C, E>>.doOnEvent(
    crossinline onLoading: (L) -> Unit = {},
    crossinline onError: (E) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Observable<LCE<L, C, E>> {
    return doOnNext {
        it.fold(
            onLoading = onLoading,
            onError = onError,
            onContent = onContent
        )
    }
}

@JvmName("doOnEventUCE")
inline fun <C, E> Observable<UCE<C, E>>.doOnEvent(
    crossinline onLoading: () -> Unit = {},
    crossinline onError: (E) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Observable<UCE<C, E>> {
    return doOnNext {
        it.fold(
            onLoading = onLoading,
            onError = onError,
            onContent = onContent
        )
    }
}

@JvmName("doOnEventUCT")
inline fun <C> Observable<UCT<C>>.doOnEvent(
    crossinline onLoading: () -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Observable<UCT<C>> {
    return doOnNext {
        it.fold(
            onLoading = onLoading,
            onError = onError,
            onContent = onContent
        )
    }
}

@JvmName("doOnEventCE")
inline fun <C, E> Observable<CE<C, E>>.doOnEvent(
    crossinline onError: (E) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Observable<CE<C, E>> {
    return doOnNext {
        it.fold(
            onContent = onContent,
            onError = onError
        )
    }
}

@JvmName("doOnEventCT")
inline fun <C> Observable<CT<C>>.doOnEvent(
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Observable<CT<C>> {
    return doOnNext {
        it.fold(
            onContent = onContent,
            onError = onError
        )
    }
}

@JvmName("doOnEventLC")
inline fun <L, C> Observable<LC<L, C>>.doOnEvent(
    crossinline onLoading: (L) -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Observable<LC<L, C>> {
    return doOnNext {
        it.fold(
            onLoading = onLoading,
            onContent = onContent
        )
    }
}

@JvmName("doOnEventUC")
inline fun <C> Observable<UC<C>>.doOnEvent(
    crossinline onLoading: () -> Unit = {},
    crossinline onContent: (C) -> Unit = {}
): Observable<UC<C>> {
    return doOnNext {
        it.fold(
            onLoading = onLoading,
            onContent = onContent
        )
    }
}