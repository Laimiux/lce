package com.laimiux.lce

@PublishedApi
internal object Utils {
    val NOT_REACHABLE = { _: Any? ->
        throw IllegalStateException("not reachable")
    }
}
