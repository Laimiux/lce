@file:OptIn(ExperimentalCoroutinesApi::class)

package com.laimiux.lce.flow

import com.laimiux.lce.CT
import com.laimiux.lce.LC
import com.laimiux.lce.LCE
import com.laimiux.lce.UC
import com.laimiux.lce.UCE
import com.laimiux.lce.UCT
import com.laimiux.lce.fold
import com.laimiux.lce.foldTypes
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flowOf

@JvmName("onlyContentEventsUCE")
fun <C : Any, E> Flow<UCE<C, E>>.onlyContentEvents(): Flow<C> {
    return flatMapMerge {
        it.fold(
            onLoading = { emptyFlow() },
            onError = { emptyFlow() },
            onContent = { flowOf(it) }
        )
    }
}

@JvmName("onlyContentEventsUCT")
fun <C : Any> Flow<UCT<C>>.onlyContentEvents(): Flow<C> {
    return flatMapMerge {
        it.fold(
            onLoading = { emptyFlow() },
            onError = { emptyFlow() },
            onContent = { flowOf(it) }
        )
    }
}

@JvmName("onlyContentEventsCT")
fun <C : Any> Flow<CT<C>>.onlyContentEvents(): Flow<C> {
    return flatMapMerge {
        it.fold(
            onError = { emptyFlow() },
            onContent = { flowOf(it) }
        )
    }
}

@JvmName("onlyContentEventsLC")
fun <L, C : Any> Flow<LC<L, C>>.onlyContentEvents(): Flow<C> {
    return flatMapMerge {
        it.fold(
            onLoading = { emptyFlow() },
            onContent = { flowOf(it) }
        )
    }
}

@JvmName("onlyContentEventsUC")
fun <C : Any> Flow<UC<C>>.onlyContentEvents(): Flow<C> {
    return flatMapMerge {
        it.fold(
            onLoading = { emptyFlow() },
            onContent = { flowOf(it) }
        )
    }
}

@JvmName("flatMapLatestContentLCE")
inline fun <L, C, E, NewC> Flow<LCE<L, C, E>>.flatMapLatestContent(
    crossinline transform: (C) -> Flow<LCE<L, NewC, E>>
): Flow<LCE<L, NewC, E>> {
    return flatMapLatest {
        it.foldTypes(
            onLoading = { flowOf(it) },
            onContent = { transform(it.value) },
            onError = { flowOf(it) }
        )
    }
}

@JvmName("flatMapLatestContentContentUCE")
inline fun <C, E, NewC> Flow<UCE<C, E>>.flatMapLatestContent(
    crossinline transform: (C) -> Flow<UCE<NewC, E>>
): Flow<UCE<NewC, E>> {
    return flatMapLatest {
        it.foldTypes(
            onLoading = { flowOf(it) },
            onContent = { transform(it.value) },
            onError = { flowOf(it) }
        )
    }
}

@JvmName("flatMapLatestContentContentUCT")
inline fun <C, NewC> Flow<UCT<C>>.flatMapLatestContent(
    crossinline transform: (C) -> Flow<UCT<NewC>>
): Flow<UCT<NewC>> {
    return flatMapLatest {
        it.foldTypes(
            onLoading = { flowOf(it) },
            onContent = { transform(it.value) },
            onError = { flowOf(it) }
        )
    }
}

@JvmName("flatMapLatestContentContentCT")
inline fun <C, NewC> Flow<CT<C>>.flatMapLatestContent(
    crossinline transform: (C) -> Flow<CT<NewC>>
): Flow<CT<NewC>> {
    return flatMapLatest {
        it.foldTypes(
            onContent = { transform(it.value) },
            onError = { flowOf(it) }
        )
    }
}

@JvmName("flatMapLatestContentContentLC")
inline fun <L, C, NewC> Flow<LC<L, C>>.flatMapLatestContent(
    crossinline transform: (C) -> Flow<LC<L, NewC>>
): Flow<LC<L, NewC>> {
    return flatMapLatest {
        it.foldTypes(
            onLoading = { flowOf(it) },
            onContent = { transform(it.value) }
        )
    }
}

@JvmName("flatMapLatestContentContentUC")
inline fun <C, NewC> Flow<UC<C>>.flatMapLatestContent(
    crossinline transform: (C) -> Flow<UC<NewC>>
): Flow<UC<NewC>> {
    return flatMapLatest {
        it.foldTypes(
            onLoading = { flowOf(it) },
            onContent = { transform(it.value) }
        )
    }
}