package com.laimiux.lce.rxjava3

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import io.reactivex.rxjava3.core.Observable

@JvmName("doOnContentLCE")
inline fun <L, C, E> Observable<LCE<L, C, E>>.doOnContent(
    crossinline action: (C) -> Unit
): Observable<LCE<L, C, E>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentUCE")
inline fun <C, E> Observable<UCE<C, E>>.doOnContent(
    crossinline action: (C) -> Unit
): Observable<UCE<C, E>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentUCT")
inline fun <C> Observable<UCT<C>>.doOnContent(
    crossinline action: (C) -> Unit
): Observable<UCT<C>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentCE")
inline fun <C, E> Observable<CE<C, E>>.doOnContent(
    crossinline action: (C) -> Unit
): Observable<CE<C, E>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentCT")
inline fun <C> Observable<CT<C>>.doOnContent(
    crossinline action: (C) -> Unit
): Observable<CT<C>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentLC")
inline fun <L, C> Observable<LC<L, C>>.doOnContent(
    crossinline action: (C) -> Unit
): Observable<LC<L, C>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentUC")
inline fun <C> Observable<UC<C>>.doOnContent(
    crossinline action: (C) -> Unit
): Observable<UC<C>> {
    return doOnEvent(
        onContent = action
    )
}