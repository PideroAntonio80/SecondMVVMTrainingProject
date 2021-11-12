package com.example.secondmvvmtrainingproject.data.network.service.base

import com.example.secondmvvmtrainingproject.data.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitApiClientGenerator : ApiClientGenerator {

    private val retrofit: Retrofit

    init {
        val builder = Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .addConverterFactory(jsonConverterFactory())
            .client(buildHttpClient())

        retrofit = builder.build()
    }

    override fun <T> generateApi(service: Class<T>): T {
        return retrofit.create(service)
    }

    private fun getBaseUrl(): String {
        return BuildConfig.API_BASE_URL
    }

    private fun jsonConverterFactory(): GsonConverterFactory {
        val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .create()

        return GsonConverterFactory.create(gson)
    }

    private fun buildHttpClient(): OkHttpClient {
        val httpClientBuilder = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor())
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)

        return httpClientBuilder.build()
    }

    private fun httpLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}