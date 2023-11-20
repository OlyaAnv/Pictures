package com.example.pictures.domain.repo

import com.example.internet_module.PhotoList
import com.example.internet_module.getApi

class PictureRepoImpl:PictureRepo {
    override suspend fun loadPictures(number_of_pictures:Int): PhotoList {
        return getApi().getDataFromInternet(number_of_pictures.toString())
    }
}