package com.laimiux.lce

inline fun <L, C, E> LCE<L, C, E>.contentOrElse(crossinline onOther: () -> C): C {
    return foldContent(
        onContent = { it },
        onOther = onOther
    )
}

inline fun <C, E> UCE<C, E>.contentOrElse(crossinline onOther: () -> C): C {
    return foldContent(
        onContent = { it },
        onOther = onOther
    )
}

inline fun <C> UCT<C>.contentOrElse(crossinline onOther: () -> C): C {
    return foldContent(
        onContent = { it },
        onOther = onOther
    )
}

inline fun <C, E> CE<C, E>.contentOrElse(crossinline onOther: () -> C): C {
    return fold(
        onContent = { it },
        onError = { onOther() }
    )
}

inline fun <C> CT<C>.contentOrElse(crossinline onOther: () -> C): C {
    return fold(
        onContent = { it },
        onError = { onOther() }
    )
}

inline fun <L, C> LC<L, C>.contentOrElse(crossinline onOther: () -> C): C {
    return fold(
        onContent = { it },
        onLoading = { onOther() }
    )
}

inline fun <C> UC<C>.contentOrElse(crossinline onOther: () -> C): C {
    return fold(
        onContent = { it },
        onLoading = { onOther() }
    )
}