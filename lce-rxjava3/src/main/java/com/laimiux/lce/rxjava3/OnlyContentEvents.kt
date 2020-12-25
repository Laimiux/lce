package com.laimiux.lce.rxjava3

import com.laimiux.lce.CT
import com.laimiux.lce.UC
import com.laimiux.lce.UCT
import com.laimiux.lce.fold
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@JvmName("onlyContentEventsUCT")
fun <T : Any> Single<UCT<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsUCT")
fun <T : Any> Maybe<UCT<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsUCT")
fun <T : Any> Observable<UCT<T>>.onlyContentEvents(): Observable<T> {
    return flatMap {
        it.fold(
            onLoading = { Observable.empty() },
            onError = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}

@JvmName("onlyContentEventsCT")
fun <T : Any> Single<CT<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsCT")
fun <T : Any> Maybe<CT<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsCT")
fun <T : Any> Observable<CT<T>>.onlyContentEvents(): Observable<T> {
    return flatMap {
        it.fold(
            onError = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}

@JvmName("onlyContentEventsUC")
fun <T : Any> Single<UC<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsUC")
fun <T : Any> Maybe<UC<T>>.onlyContentEvents(): Observable<T> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsUC")
fun <T : Any> Observable<UC<T>>.onlyContentEvents(): Observable<T> {
    return flatMap {
        it.fold(
            onLoading = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}
