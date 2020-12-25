package com.laimiux.lce

fun <T> T.toUCT() = UCT.content(this)
fun Throwable.toUCT() = UCT.error(this)
