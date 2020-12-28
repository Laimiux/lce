package com.laimiux.lce.rxjava3

import com.laimiux.lce.CT
import com.laimiux.lce.UCT
import com.laimiux.lce.asCT
import io.reactivex.rxjava3.core.Observable

/**
 * Converts [UCT] type to [CT]. Essentially it ignores loading events.
 */
fun <C> Observable<UCT<C>>.toCT(): Observable<CT<C>> {
    return switchMap {
        val ct = it.asCT()
        if (ct != null) {
            Observable.just(ct)
        } else {
            Observable.empty()
        }
    }
}