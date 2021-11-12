package com.example.secondmvvmtrainingproject.domain.usecase.base

/**
 * Class backed by a Map, used to pass parameters to a use case (UseCase) instances.
 */
@Suppress("unused")
class UseCaseParams private constructor() {

    private val parameters: MutableMap<String, Any> = mutableMapOf()

    companion object {
        fun create(): UseCaseParams = UseCaseParams()
    }

    // ----------- //
    // --  PUT  -- //
    // ----------- //

    fun putString(key: String, value: String?) = parameters.put(key, value ?: "")

    fun putInt(key: String, value: Int) = parameters.put(key, value)

    fun putLong(key: String, value: Long) = parameters.put(key, value)

    fun putBoolean(key: String, value: Boolean) = parameters.put(key, value)

    fun putObject(key: String, obj: Any?) = parameters.put(key, obj ?: Unit)

    // ----------- //
    // --  GET  -- //
    // ----------- //

    fun getString(key: String, defaultValue: String = ""): String {
        return try {
            val string = parameters[key] as? String

            if (string.isNullOrBlank()) {
                defaultValue
            } else {
                string
            }
        } catch (e: Exception) {
            defaultValue
        }
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {

        return try {
            (parameters[key] as? Int) ?: defaultValue
        } catch (e: Exception) {
            defaultValue
        }
    }

    fun getLong(key: String, defaultValue: Long = 0L): Long {

        return try {
            (parameters[key] as? Long) ?: defaultValue
        } catch (e: Exception) {
            defaultValue
        }
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {

        return try {
            (parameters[key] as? Boolean) ?: defaultValue
        } catch (e: Exception) {
            defaultValue
        }
    }

    fun getAny(key: String): Any? {

        return try {
            parameters[key]
        } catch (e: Exception) {
            null
        }
    }

    inline fun <reified T> getObject(key: String): T? {

        return (getAny(key) as? T)
    }
}
