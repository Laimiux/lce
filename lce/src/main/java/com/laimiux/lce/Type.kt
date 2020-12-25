package com.laimiux.lce

/**
 * A sealed class that supports all of LCE types and subtypes.
 */
sealed class Type<out L, out C, out E> : LCE<L, C, E> {
    sealed class Loading<T>() :
        Type<T, Nothing, Nothing>(),
        LCE<T, Nothing, Nothing> {

        companion object {
            operator fun invoke() = Unit

            @Suppress("UNUSED_PARAMETER")
            operator fun invoke(unit: Unit) = Unit

            @Suppress("UNCHECKED_CAST")
            operator fun <T> invoke(type: T): Loading<T>  {
                return if (type == kotlin.Unit) {
                    Unit as Loading<T>
                } else {
                    Typed(type)
                }
            }
        }

        abstract val value: T

        override fun isLoading(): Boolean = true
        override fun isContent(): Boolean = false
        override fun isError(): Boolean = false

        override fun contentOrNull(): Nothing? = null
        override fun errorOrNull(): Nothing? = null
        override fun loadingOrNull(): T = value

        override fun asLceType(): Type<T, Nothing, Nothing> = this

        object Unit : Loading<kotlin.Unit>(),
            UCT<Nothing>,
            UC<Nothing> {

            override val value: kotlin.Unit = kotlin.Unit

            override fun isLoading(): Boolean = true
            override fun isContent(): Boolean = false
            override fun isError(): Boolean = false

            override fun contentOrNull(): Nothing? = null
            override fun errorOrNull(): Nothing? = null
            override fun loadingOrNull(): kotlin.Unit = kotlin.Unit

            override fun asLceType(): Type<kotlin.Unit, Nothing, Nothing> = this
        }

        data class Typed<T> @PublishedApi internal constructor(
            override val value: T
        ) : Loading<T>() {

        }
    }

    sealed class Error<out T>() :
        Type<Nothing, Nothing, T>(),
        LCE<Nothing, Nothing, T>,
        CE<Nothing, T> {

        companion object {
            operator fun invoke(throwable: Throwable) = ThrowableError(throwable)

            @Suppress("UNCHECKED_CAST")
            operator fun <T> invoke(value: T): Error<T> {
                return if (value is Throwable) {
                    ThrowableError(value) as Error<T>
                } else {
                    Typed(value)
                }
            }
        }

        abstract val value: T

        override fun isLoading(): Boolean = false
        override fun isContent(): Boolean = false
        override fun isError(): Boolean = true

        override fun contentOrNull(): Nothing? = null
        override fun errorOrNull(): T = value
        override fun loadingOrNull(): Nothing? = null

        override fun asLceType(): Type<Nothing, Nothing, T> = this

        data class Typed<T> @PublishedApi internal constructor(
            override val value: T
        ) : Error<T>() {

            inline fun mapError(
                crossinline map: (T) -> Throwable
            ): Type.Error.ThrowableError {
                return ThrowableError(map(value))
            }

            inline fun <ErrorT> mapError(
                crossinline map: (T) -> ErrorT
            ): Error<ErrorT> {
                return Error(map(value))
            }

            inline fun <NewT> flatMapError(
                crossinline map: (T) -> NewT
            ): NewT {
                return map(value)
            }
        }

        data class ThrowableError @PublishedApi internal constructor(
            override val value: Throwable
        ) : Error<Throwable>(),
            UCT<Nothing>,
            CT<Nothing> {

            override fun errorOrNull(): Throwable = value

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
            is Error -> onError(value)
            is Content -> onContent(value)
        }
    }
}
