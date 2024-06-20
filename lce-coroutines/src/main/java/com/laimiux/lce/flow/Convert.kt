@file:OptIn(ExperimentalCoroutinesApi::class)

package com.laimiux.lce.flow

import com.laimiux.lce.CT
import com.laimiux.lce.UCT
import com.laimiux.lce.asCT
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

/**
 * Converts [UCT] type to [CT]. Essentially it ignores loading events.
 */
fun <C> Flow<UCT<C>>.toCT(): Flow<CT<C>> {
    return flatMapLatest {
        val ct = it.asCT()
        if (ct != null) {
            flowOf(ct)
        } else {
            emptyFlow()
        }
    }
}