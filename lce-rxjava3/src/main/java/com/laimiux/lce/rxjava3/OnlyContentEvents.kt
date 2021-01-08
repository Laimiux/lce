package com.laimiux.lce.rxjava3

import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.fold
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@JvmName("onlyContentEventsUCE")
fun <C : Any, E> Single<UCE<C, E>>.onlyContentEvents(): Observable<C> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsUCE")
fun <C : Any, E> Maybe<UCE<C, E>>.onlyContentEvents(): Observable<C> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsUCE")
fun <C : Any, E> Observable<UCE<C, E>>.onlyContentEvents(): Observable<C> {
    return flatMap {
        it.fold(
            onLoading = { Observable.empty() },
            onError = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}

@JvmName("onlyContentEventsUCT")
fun <C : Any> Single<UCT<C>>.onlyContentEvents(): Observable<C> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsUCT")
fun <C : Any> Maybe<UCT<C>>.onlyContentEvents(): Observable<C> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsUCT")
fun <C : Any> Observable<UCT<C>>.onlyContentEvents(): Observable<C> {
    return flatMap {
        it.fold(
            onLoading = { Observable.empty() },
            onError = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}

@JvmName("onlyContentEventsCT")
fun <C : Any> Single<CT<C>>.onlyContentEvents(): Observable<C> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsCT")
fun <C : Any> Maybe<CT<C>>.onlyContentEvents(): Observable<C> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsCT")
fun <C : Any> Observable<CT<C>>.onlyContentEvents(): Observable<C> {
    return flatMap {
        it.fold(
            onError = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}

@JvmName("onlyContentEventsLC")
fun <L, C : Any> Single<LC<L, C>>.onlyContentEvents(): Observable<C> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsLC")
fun <L, C : Any> Maybe<LC<L, C>>.onlyContentEvents(): Observable<C> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsLC")
fun <L, C : Any> Observable<LC<L, C>>.onlyContentEvents(): Observable<C> {
    return flatMap {
        it.fold(
            onLoading = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}

@JvmName("onlyContentEventsUC")
fun <C : Any> Single<UC<C>>.onlyContentEvents(): Observable<C> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsUC")
fun <C : Any> Maybe<UC<C>>.onlyContentEvents(): Observable<C> {
    return toObservable().onlyContentEvents()
}

@JvmName("onlyContentEventsUC")
fun <C : Any> Observable<UC<C>>.onlyContentEvents(): Observable<C> {
    return flatMap {
        it.fold(
            onLoading = { Observable.empty() },
            onContent = { Observable.just(it) }
        )
    }
}
