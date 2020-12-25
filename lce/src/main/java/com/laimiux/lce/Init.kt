package com.laimiux.lce

fun <T> T.toLCE() = LCE.content(this)
fun Throwable.toLCE() = LCE.error(this)

fun <T> T.toUCT() = UCT.content(this)
fun Throwable.toUCT() = UCT.error(this)