package com.example.pictures.domain.repo

import com.example.internet_module.PhotoList
import com.example.internet_module.getApi
import timber.log.Timber

class PictureRepoImpl:PictureRepo {
    override suspend fun loadPictures(number_of_pictures:Int): PhotoList {
        return getApi().getDataFromInternet(number_of_pictures.toString())
    }

    override suspend fun loadPicturesWithoutCash(number_of_pictures: Int): PhotoList {
        Timber.tag("mylog").d("without cash retrofit")
        return getApi().getDataFromInternetWithoutCash(number_of_pictures.toString())
    }
}