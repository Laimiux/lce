package com.laimiux.lce.rxjava3

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

inline fun <C, E> Completable.toUCE(
    value: C,
    crossinline mapError: (Throwable) -> E
): Observable<UCE<C, E>> {
    return toSingleDefault(value).toUCE(mapError)
}

inline fun <C, E> Single<C>.toUCE(
    crossinline mapError: (Throwable) -> E
): Observable<UCE<C, E>> {
    return toObservable().toUCE(mapError)
}

inline fun <C, E> Maybe<C>.toUCE(
    crossinline mapError: (Throwable) -> E
): Observable<UCE<C, E>> {
    return toObservable().toUCE(mapError)
}

inline fun <C, E> Observable<C>.toUCE(
    crossinline mapError: (Throwable) -> E
): Observable<UCE<C, E>> {
    return this
        .map { UCE.content(it) as UCE<C, E> }
        .startWithItem(UCE.loading())
        .onErrorReturn { UCE.error(mapError(it)) }
}

fun <C : Any> Completable.toUCT(value: C): Observable<UCT<C>> {
    return toSingleDefault(value).toUCT()
}

fun <C : Any> Single<C>.toUCT(): Observable<UCT<C>> {
    return toObservable().toUCT()
}

fun <C : Any> Maybe<C>.toUCT(): Observable<UCT<C>> {
    return toObservable().toUCT()
}

fun <C : Any> Observable<C>.toUCT(): Observable<UCT<C>> {
    return this
        .map { UCT.content(it) as UCT<C> }
        .startWithItem(UCT.loading())
        .onErrorReturn { UCT.error(it) }
}

inline fun <C : Any, E> Completable.toCE(
    value: C,
    crossinline mapError: (Throwable) -> E
): Observable<CE<C, E>> {
    return toSingleDefault(value).toCE(mapError)
}

inline fun <C : Any, E> Single<C>.toCE(
    crossinline mapError: (Throwable) -> E
): Observable<CE<C, E>> {
    return toObservable().toCE(mapError)
}

inline fun <C : Any, E> Maybe<C>.toCE(
    crossinline mapError: (Throwable) -> E
): Observable<CE<C, E>> {
    return toObservable().toCE(mapError)
}

inline fun <C : Any, E> Observable<C>.toCE(
    crossinline mapError: (Throwable) -> E
): Observable<CE<C, E>> {
    return this
        .map { CE.content(it) as CE<C, E> }
        .onErrorReturn { CE.error(mapError(it)) }
}

fun <C : Any> Completable.toCT(value: C): Observable<CT<C>> {
    return toSingleDefault(value).toCT()
}

fun <C : Any> Single<C>.toCT(): Observable<CT<C>> {
    return toObservable().toCT()
}

fun <C : Any> Maybe<C>.toCT(): Observable<CT<C>> {
    return toObservable().toCT()
}

fun <C : Any> Observable<C>.toCT(): Observable<CT<C>> {
    return this
        .map { CT.content(it) as CT<C> }
        .onErrorReturn { CT.error(it) }
}

fun <C : Any> Completable.toUC(value: C): Observable<UC<C>> {
    return toSingleDefault(value).toUC()
}

fun <C : Any> Single<C>.toUC(): Observable<UC<C>> {
    return toObservable().toUC()
}

fun <C : Any> Maybe<C>.toUC(): Observable<UC<C>> {
    return toObservable().toUC()
}

fun <C : Any> Observable<C>.toUC(): Observable<UC<C>> {
    return this
        .map { UC.content(it) as UC<C> }
        .startWithItem(UC.loading())
}