package com.example.internet_module

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

const val BASE_URL = "https://api.slingacademy.com/"
const val PATH = "v1/sample-data/photos"
const val QUERRY = "limit"
const val NUMBER_OF_PICTURES = 26 //max 132

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

interface RetrofitApi {
    @GET(PATH)
    suspend fun getDataFromInternet(@Query(QUERRY) limit_of_pictures: String): PhotoList

    @Headers("Cache-Control: no-cache")
    @GET(PATH)
    suspend fun getDataFromInternetWithoutCash(@Query(QUERRY) limit_of_pictures: String): PhotoList
}

fun getApi():RetrofitApi {
    return RetrofitInitializer.getRetrofit().create(RetrofitApi::class.java)
}

object RetrofitInitializer {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
}