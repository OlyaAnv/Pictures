package com.example.pictures.domain.repo

import com.example.pictures.data.models.PhotoList
import com.example.pictures.data.network.NetworkObject
import retrofit2.Response

class PictureRepoImpl:PictureRepo {
    override suspend fun loadPictures(number_of_pictures:Int): Response<PhotoList>{
        return NetworkObject.retrofitService.getDataFromInternet(number_of_pictures.toString())
    }

}