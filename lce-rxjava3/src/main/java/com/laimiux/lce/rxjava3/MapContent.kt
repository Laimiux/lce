package com.laimiux.lce.rxjava3

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LCE
import com.laimiux.lce.UCT
import com.laimiux.lce.mapContent
import io.reactivex.rxjava3.core.Observable

@JvmName("mapContentLCE")
inline fun <LoadingT, ContentT, ErrorT, V> Observable<LCE<LoadingT, ContentT, ErrorT>>.mapContent(
    crossinline transform: (ContentT) -> V
): Observable<LCE<LoadingT, V, ErrorT>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentUCT")
inline fun <T, V> Observable<UCT<T>>.mapContent(
    crossinline transform: (T) -> V
): Observable<UCT<V>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentCE")
inline fun <T, ErrorT, V> Observable<CE<T, ErrorT>>.mapContent(
    crossinline transform: (T) -> V
): Observable<CE<V, ErrorT>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentCT")
inline fun <T, V> Observable<CT<T>>.mapContent(
    crossinline transform: (T) -> V
): Observable<CT<V>> {
    return map { it.mapContent(transform) }
}