package com.example.pictures.domain.repo

import com.example.pictures.data.models.PhotoList
import retrofit2.Response

interface PictureRepo {
    suspend fun loadPictures(): Response<PhotoList>
}