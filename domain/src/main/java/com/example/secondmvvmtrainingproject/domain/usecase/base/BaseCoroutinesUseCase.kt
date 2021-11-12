package com.example.secondmvvmtrainingproject.domain.usecase.base

import android.util.Log
import com.example.secondmvvmtrainingproject.domain.model.common.AppResult
import com.example.secondmvvmtrainingproject.domain.model.common.Fail

/**
 * Abstract class for a Use Case (UseCase in terms of Clean Architecture).
 * This interface represents an execution unit per different UseCase (this means any UseCase
 * in the application should implement this contract).
 */
abstract class BaseCoroutinesUseCase<T, in P> internal constructor() : BaseUseCase() {

    /**
     * Builds a suspend function which will be used when executing the current [BaseCoroutinesUseCase].
     */
    protected abstract suspend fun buildUseCase(params: P?): AppResult<T>

    /**
     * Returns the result of [buildUseCase].
     */
    suspend fun build(params: P?): AppResult<T> {
        return try {
            val result = buildUseCase(params)
            if (result is Fail) {
                Log.e(javaClass.name, Log.getStackTraceString(result.error))
            }
            result
        } catch (ex: Exception) {
            Log.e(javaClass.name, Log.getStackTraceString(ex))

            Fail()
        }
    }
}