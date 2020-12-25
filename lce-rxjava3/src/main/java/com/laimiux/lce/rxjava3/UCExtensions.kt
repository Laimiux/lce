package com.laimiux.lce.rxjava3

import com.laimiux.lce.Type
import com.laimiux.lce.UC
import com.laimiux.lce.fold
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

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

@Suppress("UNCHECKED_CAST")
inline fun <T, V> Observable<UC<T>>.switchMapContent(
    crossinline transform: (T) -> Observable<UC<V>>
): Observable<UC<V>> {
    return switchMap {
        when (val type = it.asLceType()) {
            is Type.Content -> transform(type.value)
            else -> Observable.just(it as UC<V>)
        }
    }
}

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

inline fun <T> Observable<UC<T>>.doOnContent(
    crossinline action: (T) -> Unit
): Observable<UC<T>> {
    return doOnEvent(
        onContent = action
    )
}

fun <T : Any> Single<UC<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

fun <T : Any> Maybe<UC<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

fun <T : Any> Observable<UC<T>>.onlyContentEvents(): Observable<T> {
    return flatMap {
        it.fold(
            onLoading = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}
