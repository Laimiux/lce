package com.laimiux.lce.rxjava3

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LCE
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.mapContent
import io.reactivex.rxjava3.core.Observable

@JvmName("mapContentLCE")
inline fun <L, C, E, NewC> Observable<LCE<L, C, E>>.mapContent(
    crossinline transform: (C) -> NewC
): Observable<LCE<L, NewC, E>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentUCE")
inline fun <C, E, NewC> Observable<UCE<C, E>>.mapContent(
    crossinline transform: (C) -> NewC
): Observable<UCE<NewC, E>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentUCT")
inline fun <C, NewC> Observable<UCT<C>>.mapContent(
    crossinline transform: (C) -> NewC
): Observable<UCT<NewC>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentCE")
inline fun <C, E, NewC> Observable<CE<C, E>>.mapContent(
    crossinline transform: (C) -> NewC
): Observable<CE<NewC, E>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentCT")
inline fun <C, NewC> Observable<CT<C>>.mapContent(
    crossinline transform: (C) -> NewC
): Observable<CT<NewC>> {
    return map { it.mapContent(transform) }
}