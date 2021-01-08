package com.laimiux.lce

fun <C, E> LCE<Unit, C, E>?.orLoading(): LCE<Unit, C, E> = this ?: LCE.loading()

fun <C, E> UCE<C, E>?.orLoading(): UCE<C, E> = this ?: UCE.loading()

fun <C> UCT<C>?.orLoading(): UCT<C> = this ?: UCT.loading()

inline fun <L, C> LC<L, C>?.orLoading(value: () -> L): LC<L, C> = this ?: LC.loading(value())

fun <C> LC<Unit, C>?.orLoading(): LC<Unit, C> = this ?: LC.loading()

fun <C> UC<C>?.orLoading(): UC<C> = this ?: UC.loading()