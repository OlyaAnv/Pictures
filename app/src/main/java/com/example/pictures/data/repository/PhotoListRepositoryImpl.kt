package com.example.pictures.data.repository

import com.example.pictures.data.mapper.PhotoListMapper
import com.example.pictures.data.network.ApiFactory
import com.example.pictures.domain.Photo
import com.example.pictures.domain.PhotoListRepository
import kotlin.math.atan2

object PhotoListRepositoryImpl : PhotoListRepository {
    private val apiService = ApiFactory.getApiService()
    private val mapper = PhotoListMapper()

    override suspend fun getPhotos(numberOfPictures: Int): List<Photo> {
        return mapper.mapListNetworkModelToListEntity(
            apiService.getDataFromInternet(numberOfPictures.toString()).photos
        )
    }
}