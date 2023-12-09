package com.example.pictures.data.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {
    private const val BASE_URL = "https://api.slingacademy.com/"

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val interceptor = HttpLoggingInterceptor()

    private fun setInterceptorLevel():HttpLoggingInterceptor {
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(setInterceptorLevel())
        .build()

    fun getApiService(): ApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ApiService::class.java)
    }
}