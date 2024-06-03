package com.laimiux.lce.flow

import com.laimiux.lce.CE
import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.mapContent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@JvmName("mapContentLCE")
inline fun <L, C, E, NewC> Flow<LCE<L, C, E>>.mapContent(
    crossinline transform: (C) -> NewC
): Flow<LCE<L, NewC, E>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentUCE")
inline fun <C, E, NewC> Flow<UCE<C, E>>.mapContent(
    crossinline transform: (C) -> NewC
): Flow<UCE<NewC, E>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentUCT")
inline fun <C, NewC> Flow<UCT<C>>.mapContent(
    crossinline transform: (C) -> NewC
): Flow<UCT<NewC>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentCE")
inline fun <C, E, NewC> Flow<CE<C, E>>.mapContent(
    crossinline transform: (C) -> NewC
): Flow<CE<NewC, E>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentCT")
inline fun <C, NewC> Flow<CT<C>>.mapContent(
    crossinline transform: (C) -> NewC
): Flow<CT<NewC>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentLC")
inline fun <L, C, NewC> Flow<LC<L, C>>.mapContent(
    crossinline transform: (C) -> NewC
): Flow<LC<L, NewC>> {
    return map { it.mapContent(transform) }
}

@JvmName("mapContentUC")
inline fun <C, NewC> Flow<UC<C>>.mapContent(
    crossinline transform: (C) -> NewC
): Flow<UC<NewC>> {
    return map { it.mapContent(transform) }
}