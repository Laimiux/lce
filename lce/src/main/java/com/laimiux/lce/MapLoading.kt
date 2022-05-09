package com.laimiux.lce


/**
 * Replaces [UCT.loading] with [content] value if it is not null.
 */
fun <C : Any> UCT<C>.mapLoading(content: C?): UCT<C> {
    return if (content == null) {
        this
    } else {
        flatMapLoading { UCT.content(content) }
    }
}

/**
 * Replaces [UCE.loading] with [content] value if it is not null.
 */
fun <C : Any, E> UCE<C, E>.mapLoading(content: C?): UCE<C, E> {
    return if (content == null) {
        this
    } else {
        flatMapLoading { UCE.content(content) }
    }
}

/**
 * Replaces [LCE.loading] with [content] value if it is not null.
 */
fun <L, C : Any, E> LCE<L, C, E>.mapLoading(content: C?): LCE<L, C, E> {
    return if (content == null) {
        this
    } else {
        flatMapLoading { LCE.content(content) }
    }
}