package com.laimiux.lce.rxjava3

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.UC
import com.laimiux.lce.UCT
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

fun <T> Completable.toUCT(value: T): Observable<UCT<T>> {
    return toSingleDefault(value).toUCT()
}

fun <T> Single<T>.toUCT(): Observable<UCT<T>> {
    return toObservable().toUCT()
}

fun <T> Maybe<T>.toUCT(): Observable<UCT<T>> {
    return toObservable().toUCT()
}

fun <T> Observable<T>.toUCT(): Observable<UCT<T>> {
    return this
        .map { UCT.content(it) as UCT<T> }
        .startWithItem(UCT.loading())
        .onErrorReturn { UCT.error(it) }
}

inline fun <C, E> Completable.toCE(
    value: C,
    crossinline mapError: (Throwable) -> E
): Observable<CE<C, E>> {
    return toSingleDefault(value).toCE(mapError)
}

inline fun <C, E> Single<C>.toCE(
    crossinline mapError: (Throwable) -> E
): Observable<CE<C, E>> {
    return toObservable().toCE(mapError)
}

inline fun <C, E> Maybe<C>.toCT(
    crossinline mapError: (Throwable) -> E
): Observable<CE<C, E>> {
    return toObservable().toCE(mapError)
}

inline fun <C, E> Observable<C>.toCE(
    crossinline mapError: (Throwable) -> E
): Observable<CE<C, E>> {
    return this
        .map { CE.content(it) as CE<C, E> }
        .onErrorReturn { CE.error(mapError(it)) }
}

fun <T> Completable.toCT(value: T): Observable<CT<T>> {
    return toSingleDefault(value).toCT()
}

fun <T> Single<T>.toCT(): Observable<CT<T>> {
    return toObservable().toCT()
}

fun <T> Maybe<T>.toCT(): Observable<CT<T>> {
    return toObservable().toCT()
}

fun <T> Observable<T>.toCT(): Observable<CT<T>> {
    return this
        .map { CT.content(it) as CT<T> }
        .onErrorReturn { CT.error(it) }
}

fun <T> Completable.toUC(value: T): Observable<UC<T>> {
    return toSingleDefault(value).toUC()
}

fun <T> Single<T>.toUC(): Observable<UC<T>> {
    return toObservable().toUC()
}

fun <T> Maybe<T>.toUC(): Observable<UC<T>> {
    return toObservable().toUC()
}

fun <T> Observable<T>.toUC(): Observable<UC<T>> {
    return this
        .map { UC.content(it) as UC<T> }
        .startWithItem(UC.loading())
}