package com.laimiux.lce.rxjava3

import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.Type
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.fold
import com.laimiux.lce.foldTypes
import io.reactivex.rxjava3.core.Observable

@JvmName("switchMapContentLCE")
inline fun <L, C, E, NewC> Observable<LCE<L, C, E>>.switchMapContent(
    crossinline transform: (C) -> Observable<LCE<L, NewC, E>>
): Observable<LCE<L, NewC, E>> {
    return switchMap {
        it.foldTypes(
            onLoading = { Observable.just(it) },
            onContent = { transform(it.value) },
            onError = { Observable.just(it) }
        )
    }
}

@JvmName("switchMapContentUCE")
inline fun <C, E, NewC> Observable<UCE<C, E>>.switchMapContent(
    crossinline transform: (C) -> Observable<UCE<NewC, E>>
): Observable<UCE<NewC, E>> {
    return switchMap {
        it.foldTypes(
            onLoading = { Observable.just(it) },
            onContent = { transform(it.value) },
            onError = { Observable.just(it) }
        )
    }
}

@JvmName("switchMapContentUCT")
inline fun <C, NewC> Observable<UCT<C>>.switchMapContent(
    crossinline transform: (C) -> Observable<UCT<NewC>>
): Observable<UCT<NewC>> {
    return switchMap {
        it.foldTypes(
            onLoading = { Observable.just(it) },
            onContent = { transform(it.value) },
            onError = { Observable.just(it) }
        )
    }
}

@JvmName("switchMapContentCT")
inline fun <C, NewC> Observable<CT<C>>.switchMapContent(
    crossinline transform: (C) -> Observable<CT<NewC>>
): Observable<CT<NewC>> {
    return switchMap {
        it.foldTypes(
            onContent = { transform(it.value) },
            onError = { Observable.just(it) }
        )
    }
}

@JvmName("switchMapContentLC")
inline fun <L, C, NewC> Observable<LC<L, C>>.switchMapContent(
    crossinline transform: (C) -> Observable<LC<L, NewC>>
): Observable<LC<L, NewC>> {
    return switchMap {
        it.foldTypes(
            onLoading = { Observable.just(it) },
            onContent = { transform(it.value) }
        )
    }
}

@JvmName("switchMapContentUC")
inline fun <C, NewC> Observable<UC<C>>.switchMapContent(
    crossinline transform: (C) -> Observable<UC<NewC>>
): Observable<UC<NewC>> {
    return switchMap {
        it.foldTypes(
            onLoading = { Observable.just(it) },
            onContent = { transform(it.value) }
        )
    }
}