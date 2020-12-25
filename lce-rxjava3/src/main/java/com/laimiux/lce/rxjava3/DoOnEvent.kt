package com.laimiux.lce.rxjava3

import com.laimiux.lce.CT
import com.laimiux.lce.UC
import com.laimiux.lce.UCT
import com.laimiux.lce.fold
import io.reactivex.rxjava3.core.Observable

@JvmName("doOnEventUCT")
inline fun <T> Observable<UCT<T>>.doOnEvent(
    crossinline onLoading: () -> Unit = {},
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onContent: (T) -> Unit = {}
): Observable<UCT<T>> {
    return doOnNext {
        it.fold(
            onLoading = { onLoading() },
            onError = onError,
            onContent = onContent
        )
    }
}

@JvmName("doOnEventCT")
inline fun <T> Observable<CT<T>>.doOnEvent(
    crossinline onError: (Throwable) -> Unit = {},
    crossinline onContent: (T) -> Unit = {}
): Observable<CT<T>> {
    return doOnNext {
        it.fold(
            onContent = onContent,
            onError = onError
        )
    }
}

@JvmName("doOnEventUC")
inline fun <T> Observable<UC<T>>.doOnEvent(
    crossinline onLoading: () -> Unit = {},
    crossinline onContent: (T) -> Unit = {}
): Observable<UC<T>> {
    return doOnNext {
        it.fold(
            onLoading = { onLoading() },
            onContent = onContent
        )
    }
}