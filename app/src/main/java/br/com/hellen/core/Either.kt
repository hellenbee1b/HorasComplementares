package br.com.hellen.core

@Suppress("UNCHECKED_CAST")
sealed class Either<out A, out B> {
    data class Left<out A>(val leftVal: A) : Either<A, Nothing>()
    data class Right<out B>(val rightVal: B) : Either<Nothing, B>()

    companion object {
        fun <B> right(value: B): Either<Nothing, B> =
            Right(value)
        fun <A> left(value: A): Either<A, Nothing> =
            Left(value)
    }

    fun <T> getValue(): T = when (this) {
        is Left -> this.leftVal as T
        is Right -> this.rightVal as T
    }

    fun <T> fold(leftOp: (A) -> T, rightOp: (B) -> T): T = when (this) {
        is Left -> leftOp(this.leftVal)
        is Right -> rightOp(this.rightVal)
    }
}
