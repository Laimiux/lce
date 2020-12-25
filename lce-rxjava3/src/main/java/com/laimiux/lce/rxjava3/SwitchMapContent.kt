package com.laimiux.lce.rxjava3

import com.laimiux.lce.CT
import com.laimiux.lce.Type
import com.laimiux.lce.UC
import com.laimiux.lce.UCT
import com.laimiux.lce.fold
import com.laimiux.lce.foldTypes
import io.reactivex.rxjava3.core.Observable

@JvmName("switchMapContentUCT")
inline fun <T, V> Observable<UCT<T>>.switchMapContent(
    crossinline transform: (T) -> Observable<UCT<V>>
): Observable<UCT<V>> {
    return switchMap {
        it.foldTypes(
            onLoading = { Observable.just(it) },
            onContent = { transform(it.value) },
            onError = { Observable.just(it) }
        )
    }
}

@JvmName("switchMapContentCT")
inline fun <T, V> Observable<CT<T>>.switchMapContent(
    crossinline transform: (T) -> Observable<CT<V>>
): Observable<CT<V>> {
    return switchMap {
        it.foldTypes(
            onContent = { transform(it.value) },
            onError = { Observable.just(it) }
        )
    }
}

@JvmName("switchMapContentUC")
inline fun <T, V> Observable<UC<T>>.switchMapContent(
    crossinline transform: (T) -> Observable<UC<V>>
): Observable<UC<V>> {
    return switchMap {
        it.foldTypes(
            onLoading = { Observable.just(it) },
            onContent = { transform(it.value) }
        )
    }
}