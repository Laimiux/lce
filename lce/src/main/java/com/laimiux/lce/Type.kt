package com.laimiux.lce

/**
 * A sealed class that supports all of LCE types and subtypes.
 */
sealed class Type<out L, out C, out E> : LCE<L, C, E> {
    object UnitLoading :
        Type<Unit, Nothing, Nothing>(),
        LCE<Unit, Nothing, Nothing>,
        UCT<Nothing>,
        UC<Nothing> {

        override fun isLoading(): Boolean = true
        override fun isContent(): Boolean = false
        override fun isError(): Boolean = false

        override fun contentOrNull(): Nothing? = null
        override fun errorOrNull(): Nothing? = null
        override fun loadingOrNull(): Unit = Unit

        override fun asLceType(): Type<Unit, Nothing, Nothing> = this
    }

    data class Loading<T> internal constructor(
        val value: T
    ) :
        Type<T, Nothing, Nothing>(),
        LCE<T, Nothing, Nothing> {

        override fun isLoading(): Boolean = true
        override fun isContent(): Boolean = false
        override fun isError(): Boolean = false

        override fun contentOrNull(): Nothing? = null
        override fun errorOrNull(): Nothing? = null
        override fun loadingOrNull(): T = value

        override fun asLceType(): Type<T, Nothing, Nothing> = this
    }

    data class Error<T> @PublishedApi internal constructor(
        val value: T
    ) :
        Type<Nothing, Nothing, T>(),
        LCE<Nothing, Nothing, T>,
        CE<Nothing, T> {

        override fun isLoading(): Boolean = false
        override fun isContent(): Boolean = false
        override fun isError(): Boolean = true

        override fun contentOrNull(): Nothing? = null
        override fun errorOrNull(): T = value
        override fun loadingOrNull(): Nothing? = null

        override fun asLceType(): Type<Nothing, Nothing, T> = this

        inline fun mapError(
            crossinline map: (T) -> Throwable
        ): ThrowableError {
            return ThrowableError(map(value))
        }

        inline fun <ErrorT> mapError(
            crossinline map: (T) -> ErrorT
        ): Error<ErrorT> {
            return Error(map(value))
        }
    }

    data class ThrowableError @PublishedApi internal constructor(
        val value: Throwable
    ) :
        Type<Nothing, Nothing, Throwable>(),
        LCE<Nothing, Nothing, Throwable>,
        UCT<Nothing>,
        CE<Nothing, Throwable>,
        CT<Nothing> {

        override fun isLoading(): Boolean = false
        override fun isContent(): Boolean = false
        override fun isError(): Boolean = true

        override fun contentOrNull(): Nothing? = null
        override fun errorOrNull(): Throwable = value
        override fun loadingOrNull(): Nothing? = null

        override fun asLceType(): Type<Nothing, Nothing, Throwable> = this

        inline fun mapError(
            crossinline map: (Throwable) -> Throwable
        ): ThrowableError {
            return ThrowableError(map(value))
        }

        inline fun <ErrorT> mapError(
            crossinline map: (Throwable) -> ErrorT
        ): Error<ErrorT> {
            return Error(map(value))
        }

        inline fun <NewT> flatMapError(
            crossinline map: (Throwable) -> NewT
        ): NewT {
            return map(value)
        }
    }

    data class Content<out T> @PublishedApi internal constructor(
        val value: T
    ) :
        Type<Nothing, T, Nothing>(),
        LCE<Nothing, T, Nothing>,
        UCT<T>,
        UC<T>,
        CE<T, Nothing>,
        CT<T> {

        override fun isLoading(): Boolean = false
        override fun isContent(): Boolean = true
        override fun isError(): Boolean = false

        override fun contentOrNull(): T = value
        override fun errorOrNull(): Nothing? = null
        override fun loadingOrNull(): Nothing? = null

        override fun asLceType(): Type<Nothing, T, Nothing> = this

        @Suppress("UNUSED_PARAMETER")
        inline fun <ErrorT> mapError(
            crossinline map: (Nothing) -> ErrorT
        ): Content<T> {
            return this
        }

        inline fun <NewT> flatMapContent(
            crossinline transform: (T) -> NewT
        ): NewT {
            return transform(value)
        }

        @Suppress("UNUSED_PARAMETER")
        inline fun <NewT> flatMapError(
            crossinline map: (Throwable) -> NewT
        ): Content<T> {
            return this
        }
    }

    @Suppress("UNCHECKED_CAST")
    inline fun <T> fold(
        crossinline onLoading: (L) -> T,
        crossinline onError: (E) -> T,
        crossinline onContent: (C) -> T
    ): T {
        return when (this) {
            is Loading -> onLoading(value)
            is UnitLoading -> onLoading(Unit as L)
            is ThrowableError -> onError(value as E)
            is Error -> onError(value)
            is Content -> onContent(value)
        }
    }
}
