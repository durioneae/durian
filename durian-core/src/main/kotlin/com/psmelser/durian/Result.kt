package com.psmelser.durian

/**
 * This utility class is based on Scala's Either. Since the majority of uses of the Either object is as a result and the
 * main criticism it that it is unclear which side (left or right) should be the result value and which should be the
 * result error, here we have made the use explicit by naming the object Result and given a clear side for value and error.
 * @param <V> Value result
 * @param <E> Error result
</E></V> */
class Result<V, E> private constructor(val value: V?, val error: E?) {

    /**
     * Defines the projections returning a value to be performed on either the value result or error result of the
     * @param valueFunction
     * @param errorFunction
     * @param <T>
     * @return
    </T> */
    fun <T> map(valueFunction: (V) -> T,
                errorFunction: (E) -> T): T {
        return if (value != null) valueFunction.invoke(value) else error?.let { errorFunction.invoke(it) }!!
    }

    fun <T> mapValue(lFunc: (V) -> T): Result<T, E> {
        return Result(value?.let(lFunc), error)
    }

    fun <T> mapError(rFunc: (E) -> T): Result<V, T> {
        return Result(value, error?.let(rFunc))
    }

    fun apply(valueFunction: (V) -> Unit, errorFunction: (E) -> Unit) {
        value?.let(valueFunction)
        error?.let(errorFunction)
    }

    fun hasValue(): Boolean {
        return value != null
    }

    fun hasError(): Boolean {
        return error != null
    }

    fun ifValue(valueFunction: (V) -> Unit) {
        value?.let(valueFunction)
    }

    fun ifError(errorFunction: (E) -> Unit) {
        error?.let(errorFunction)
    }

    companion object {
        fun <L, R> value(value: L): Result<L, R> {
            return Result(value, null)
        }

        fun <L, R> error(error: R): Result<L, R> {
            return Result(null, error)
        }
    }
}

