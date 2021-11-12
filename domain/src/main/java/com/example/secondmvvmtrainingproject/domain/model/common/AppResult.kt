package com.example.secondmvvmtrainingproject.domain.model.common

sealed class AppResult<out T>(val complete: Boolean) {

    /**
     * Returns the Success value or null.
     * Can be invoked as an operator like: `yourProp()`
     */
    open operator fun invoke(): T? = null
}

class Loading<out T> : AppResult<T>(complete = false), Incomplete

data class Fail<out T>(val error: Throwable = Throwable()) : AppResult<T>(complete = true)

data class Success<out T>(private val value: T) : AppResult<T>(complete = true) {

    override operator fun invoke(): T = value
}

/**
 * Helper interface for using SearchEntity in a when clause for handling both Uninitialized and Loading.
 *
 * With this, you can do:
 * ```
 * when (data) {
 *     is Incomplete -> Unit
 *     is Success    -> Unit
 *     is Fail       -> Unit
 * }
 * ```
 */
interface Incomplete
