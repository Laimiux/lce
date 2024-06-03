package com.laimiux.lce.flow

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import kotlinx.coroutines.flow.Flow

@JvmName("doOnContentLCE")
inline fun <L, C, E> Flow<LCE<L, C, E>>.doOnContent(
    crossinline action: (C) -> Unit
): Flow<LCE<L, C, E>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentUCE")
inline fun <C, E> Flow<UCE<C, E>>.doOnContent(
    crossinline action: (C) -> Unit
): Flow<UCE<C, E>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentUCT")
inline fun <C> Flow<UCT<C>>.doOnContent(
    crossinline action: (C) -> Unit
): Flow<UCT<C>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentCE")
inline fun <C, E> Flow<CE<C, E>>.doOnContent(
    crossinline action: (C) -> Unit
): Flow<CE<C, E>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentCT")
inline fun <C> Flow<CT<C>>.doOnContent(
    crossinline action: (C) -> Unit
): Flow<CT<C>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentLC")
inline fun <L, C> Flow<LC<L, C>>.doOnContent(
    crossinline action: (C) -> Unit
): Flow<LC<L, C>> {
    return doOnEvent(
        onContent = action
    )
}

@JvmName("doOnContentUC")
inline fun <C> Flow<UC<C>>.doOnContent(
    crossinline action: (C) -> Unit
): Flow<UC<C>> {
    return doOnEvent(
        onContent = action
    )
}