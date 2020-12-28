package com.laimiux.lce

/**
 * A sealed class that supports all of LCE types and subtypes.
 */
sealed class Type<out L, out C, out E> : LCE<L, C, E> {
    sealed class Loading<out T>() :
        Type<T, Nothing, Nothing>(),
        LCE<T, Nothing, Nothing> {

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

        abstract val value: T

        override fun isLoading(): Boolean = true
        override fun isContent(): Boolean = false
        override fun isError(): Boolean = false

        override fun contentOrNull(): Nothing? = null
        override fun errorOrNull(): Nothing? = null
        override fun loadingOrNull(): T = value

        override fun asLceType(): Type<T, Nothing, Nothing> = this

        object UnitType : Loading<Unit>(),
            LCE<Unit, Nothing, Nothing>,
            UCE<Nothing, Nothing>,
            UCT<Nothing>,
            UC<Nothing> {

            override val value: Unit = Unit
        }

        data class Typed<out T> @PublishedApi internal constructor(
            override val value: T
        ) : Loading<T>()
    }

    sealed class Error<out T>() :
        Type<Nothing, Nothing, T>(),
        LCE<Nothing, Nothing, T>,
        UCE<Nothing, T>,
        CE<Nothing, T> {

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

        abstract val value: T

        override fun isLoading(): Boolean = false
        override fun isContent(): Boolean = false
        override fun isError(): Boolean = true

        override fun contentOrNull(): Nothing? = null
        override fun errorOrNull(): T = value
        override fun loadingOrNull(): Nothing? = null

        override fun asLceType(): Type<Nothing, Nothing, T> = this

        data class Typed<out T> @PublishedApi internal constructor(
            override val value: T
        ) : Error<T>()

        data class ThrowableType @PublishedApi internal constructor(
            override val value: Throwable
        ) : Error<Throwable>(),
            UCT<Nothing>,
            CT<Nothing> {

            override fun errorOrNull(): Throwable = value
        }
    }

    data class Content<out T> @PublishedApi internal constructor(
        val value: T
    ) :
        Type<Nothing, T, Nothing>(),
        LCE<Nothing, T, Nothing>,
        UCE<T, Nothing>,
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
    }
}
