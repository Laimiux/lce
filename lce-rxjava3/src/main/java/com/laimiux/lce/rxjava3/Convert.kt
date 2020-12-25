package com.laimiux.lce.rxjava3

import com.laimiux.lce.CT
import com.laimiux.lce.UCT
import io.reactivex.rxjava3.core.Observable

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