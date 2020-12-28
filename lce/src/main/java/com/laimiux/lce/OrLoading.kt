package com.laimiux.lce

fun <C, E> LCE<Unit, C, E>?.orLoading(): LCE<Unit, C, E> = this ?: LCE.loading()

fun <C, E> UCE<C, E>?.orLoading(): UCE<C, E> = this ?: UCE.loading()

fun <C> UCT<C>?.orLoading(): UCT<C> = this ?: UCT.loading()

fun <C> UC<C>?.orLoading(): UC<C> = this ?: UC.loading()