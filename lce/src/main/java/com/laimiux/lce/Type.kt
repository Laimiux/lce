package com.laimiux.lce

/**
 * A sealed class that supports all of LCE types and subtypes.
 */
sealed class Type<out L, out C, out E> : LCE<L, C, E> {
    /**
     * Loading type has two subclasses [Loading.UnitType] and [Loading.Typed].
     */
    sealed class Loading<out L>() :
        Type<L, Nothing, Nothing>(),
        LCE<L, Nothing, Nothing>,
        LC<L, Nothing>,
        LE<L, Nothing>,
        LT<L> {

        companion object {
            operator fun invoke() = UnitType

            @Suppress("UNUSED_PARAMETER")
            operator fun invoke(unit: Unit) = UnitType

            @Suppress("UNCHECKED_CAST")
            operator fun <T> invoke(type: T): Loading<T>  {
                return if (type == Unit) {
                    UnitType as Loading<T>
                } else {
                    Typed(type)
                }
            }
        }

        abstract val value: L

        override fun isLoading(): Boolean = true
        override fun isContent(): Boolean = false
        override fun isError(): Boolean = false

        override fun contentOrNull(): Nothing? = null
        override fun errorOrNull(): Nothing? = null
        override fun loadingOrNull(): L = value

        override fun asLceType(): Type<L, Nothing, Nothing> = this

        object UnitType : Loading<Unit>(),
            LCE<Unit, Nothing, Nothing>,
            UCE<Nothing, Nothing>,
            UCT<Nothing>,
            UC<Nothing>,
            UE<Nothing>,
            UT {

            override val value: Unit = Unit
        }

        data class Typed<out T> @PublishedApi internal constructor(
            override val value: T
        ) : Loading<T>()
    }

    /**
     * Error type has two subclasses [Error.ThrowableType] and [Error.Typed].
     */
    sealed class Error<out E>() :
        Type<Nothing, Nothing, E>(),
        LCE<Nothing, Nothing, E>,
        UCE<Nothing, E>,
        CE<Nothing, E>,
        LE<Nothing, E>,
        UE<E> {

        companion object {
            operator fun invoke(throwable: Throwable) = ThrowableType(throwable)

            @Suppress("UNCHECKED_CAST")
            operator fun <T> invoke(value: T): Error<T> {
                return if (value is Throwable) {
                    ThrowableType(value) as Error<T>
                } else {
                    Typed(value)
                }
            }
        }

        abstract val value: E

        override fun isLoading(): Boolean = false
        override fun isContent(): Boolean = false
        override fun isError(): Boolean = true

        override fun contentOrNull(): Nothing? = null
        override fun errorOrNull(): E = value
        override fun loadingOrNull(): Nothing? = null

        override fun asLceType(): Type<Nothing, Nothing, E> = this

        data class Typed<out T> @PublishedApi internal constructor(
            override val value: T
        ) : Error<T>()

        data class ThrowableType @PublishedApi internal constructor(
            override val value: Throwable
        ) : Error<Throwable>(),
            UCT<Nothing>,
            CT<Nothing>,
            LT<Nothing>,
            UT {

            override fun errorOrNull(): Throwable = value
        }
    }

    data class Content<out C> @PublishedApi internal constructor(
        val value: C
    ) :
        Type<Nothing, C, Nothing>(),
        LCE<Nothing, C, Nothing>,
        UCE<C, Nothing>,
        UCT<C>,
        LC<Nothing, C>,
        UC<C>,
        CE<C, Nothing>,
        CT<C> {

        override fun isLoading(): Boolean = false
        override fun isContent(): Boolean = true
        override fun isError(): Boolean = false

        override fun contentOrNull(): C = value
        override fun errorOrNull(): Nothing? = null
        override fun loadingOrNull(): Nothing? = null

        override fun asLceType(): Type<Nothing, C, Nothing> = this
    }
}
