package com.psmelser.durian

import java.util.Optional
import java.util.function.Consumer
import java.util.function.Function

/**
 * This utility class is based on Scala's Either. Since the majority of uses of the Either object is as a result and the
 * main criticism it that it is unclear which side (left or right) should be the result value and which should be the
 * result error, here we have made the use explicit by naming the object Result and given a clear side for value and error.
 * @param <V> Value result
 * @param <E> Error result
</E></V> */
class Result<V, E> private constructor(private val value: Optional<V>, private val error: Optional<E>) {

    /**
     * Defines the projections returning a value to be performed on either the value result or error result of the
     * @param valueFunction
     * @param errorFunction
     * @param <T>
     * @return
    </T> */
    fun <T> map(
            valueFunction: (V) -> T,
            errorFunction: (E) -> T): T {
        return value.map(valueFunction).orElseGet { error.map(errorFunction).get() }
    }

    fun <T> mapValue(lFunc: (V) -> T): Result<T, E> {
        return Result(value.map(lFunc), error)
    }

    fun <T> mapError(rFunc: (E) -> T): Result<V, T> {
        return Result(value, error.map(rFunc))
    }

    fun apply(valueFunction: (V) -> Unit, errorFunction: (E) -> Unit) {
        value.ifPresent(valueFunction)
        error.ifPresent(errorFunction)
    }

    companion object {

        fun <L, R> value(value: L): Result<L, R> {
            return Result(Optional.of(value), Optional.empty())
        }

        fun <L, R> error(error: R): Result<L, R> {
            return Result(Optional.empty(), Optional.of(error))
        }
    }
}

