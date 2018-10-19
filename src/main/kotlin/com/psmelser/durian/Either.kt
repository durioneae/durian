package com.psmelser.durian

class Either<L, R>(private val left: L? = null, private val right: R? = null) {

    fun <T> map(leftProjection: (L) -> T,
                rightProjection: (R) -> T): T {
        return if (left != null) leftProjection.invoke(left) else right?.let { rightProjection.invoke(it) }!!
    }

    fun <T> mapLeft(lProjection: (L) -> T): T? {
        return left?.let(lProjection)
    }

    fun <T> mapRight(rProjection: (R) -> T): T? {
        return right?.let(rProjection)
    }

    fun apply(leftProjection: (L) -> Unit, rightProjection: (R) -> Unit) {
        right?.let(rightProjection)
        left?.let(leftProjection)
    }

    fun isLeft(): Boolean {
        return left != null
    }

    fun isRight(): Boolean {
        return right != null
    }

    fun ifLeft(leftProjection: (L) -> Unit) {
        left?.let(leftProjection)
    }

    fun ifRight(rightProjection: (R) -> Unit) {
        right?.let(rightProjection)
    }

    companion object {
        fun <L, R> left(value: L): Either<L, R>{
            return Either(left = value)
        }

        fun <L, R> right(value: R): Either<L, R> {
            return Either(right = value)
        }
    }
}