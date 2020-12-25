package com.laimiux.lce.rxjava3

import com.laimiux.lce.CT
import com.laimiux.lce.UC
import com.laimiux.lce.UCT
import io.reactivex.rxjava3.core.Observable

@JvmName("doOnContentUCT")
inline fun <T> Observable<UCT<T>>.doOnContent(
    crossinline action: (T) -> Unit
): Observable<UCT<T>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentCT")
inline fun <T> Observable<CT<T>>.doOnContent(
    crossinline action: (T) -> Unit
): Observable<CT<T>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentUC")
inline fun <T> Observable<UC<T>>.doOnContent(
    crossinline action: (T) -> Unit
): Observable<UC<T>> {
    return doOnEvent(
        onContent = action
    )
}