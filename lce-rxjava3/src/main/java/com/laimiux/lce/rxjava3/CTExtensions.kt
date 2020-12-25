package com.laimiux.lce.rxjava3

import com.laimiux.lce.CT
import com.laimiux.lce.Type
import com.laimiux.lce.fold
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

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

@Suppress("UNCHECKED_CAST")
inline fun <T, V> Observable<CT<T>>.switchMapContent(
    crossinline transform: (T) -> Observable<CT<V>>
): Observable<CT<V>> {
    return switchMap {
        when (val type = it.asLceType()) {
            is Type.Content -> transform(type.value)
            else -> Observable.just(it as CT<V>)
        }
    }
}

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

inline fun <T> Observable<CT<T>>.doOnContent(
    crossinline action: (T) -> Unit
): Observable<CT<T>> {
    return doOnEvent(
        onContent = action
    )
}

fun <T : Any> Single<CT<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

fun <T : Any> Maybe<CT<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

fun <T : Any> Observable<CT<T>>.onlyContentEvents(): Observable<T> {
    return flatMap {
        it.fold(
            onError = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}
