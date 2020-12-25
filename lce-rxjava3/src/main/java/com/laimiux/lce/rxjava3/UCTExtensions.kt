package com.laimiux.lce.rxjava3

import com.laimiux.lce.CT
import com.laimiux.lce.Type
import com.laimiux.lce.UCT
import com.laimiux.lce.fold
import com.laimiux.lce.mapContent
import com.laimiux.lce.mapError
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

/**
 * Converts [UCT] type to [CT]. Essentially it ignores loading events.
 */
fun <T> Observable<UCT<T>>.toCT(): Observable<CT<T>> {
    return switchMap {
        val ct = it.asCT()
        if (ct != null) {
            Observable.just(ct)
        } else {
            Observable.empty()
        }
    }
}

inline fun <T, V> Observable<UCT<T>>.mapContent(
    crossinline transform: (T) -> V
): Observable<UCT<V>> {
    return map { it.mapContent(transform) }
}

inline fun <T> Observable<UCT<T>>.mapError(
    crossinline transform: (Throwable) -> Throwable
): Observable<UCT<T>> {
    return map { it.mapError(transform) }
}

@Suppress("UNCHECKED_CAST")
inline fun <T, V> Observable<UCT<T>>.switchMapContent(
    crossinline transform: (T) -> Observable<UCT<V>>
): Observable<UCT<V>> {
    return switchMap {
        when (val type = it.asLceType()) {
            is Type.Content -> transform(type.value)
            else -> Observable.just(it as UCT<V>)
        }
    }
}

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

inline fun <T> Observable<UCT<T>>.doOnContent(
    crossinline action: (T) -> Unit
): Observable<UCT<T>> {
    return doOnEvent(
        onContent = action
    )
}

fun <T : Any> Single<UCT<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

fun <T : Any> Maybe<UCT<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

fun <T : Any> Observable<UCT<T>>.onlyContentEvents(): Observable<T> {
    return flatMap {
        it.fold(
            onLoading = { Observable.empty() },
            onError = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}
