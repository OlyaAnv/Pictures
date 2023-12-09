package com.example.pictures.data.network

import com.example.pictures.data.model.PhotoDtoList
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("v1/sample-data/photos")
    suspend fun getDataFromInternet(
        @Query("limit") limitOfPictures: String
    ): PhotoDtoList
}