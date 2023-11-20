package com.example.pictures.data.network

import com.example.pictures.data.constants.BASE_URL
import com.example.pictures.data.constants.PATH
import com.example.pictures.data.constants.QUERRY
import com.example.pictures.data.models.PhotoList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface Network {
    @GET(PATH)
    suspend fun getDataFromInternet(@Query(QUERRY) limit_of_pictures:String): retrofit2.Response<PhotoList>
}

object NetworkObject {
    val retrofitService: Network by lazy {
        retrofit.create(Network::class.java)
    }
}

