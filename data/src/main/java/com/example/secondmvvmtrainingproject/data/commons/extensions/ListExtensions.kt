package com.example.secondmvvmtrainingproject.data.commons.extensions

fun <T> List<T>?.toArrayList(): ArrayList<T>? {
    return if (this != null) {
        ArrayList(this)
    } else {
        null
    }
}