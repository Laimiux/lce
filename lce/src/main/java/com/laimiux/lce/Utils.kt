package com.laimiux.lce

object Utils {
    val HANDLE_NOTHING = { nothing: Nothing? ->
        throw IllegalStateException("not reachable")
    }
}
