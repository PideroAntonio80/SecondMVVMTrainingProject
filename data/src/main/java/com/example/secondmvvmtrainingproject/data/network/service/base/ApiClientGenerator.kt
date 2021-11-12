package com.example.secondmvvmtrainingproject.data.network.service.base

interface ApiClientGenerator {
    fun <T> generateApi(service: Class<T>): T
}