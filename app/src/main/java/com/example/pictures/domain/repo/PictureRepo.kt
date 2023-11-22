package com.example.pictures.domain.repo

import com.example.internet_module.PhotoList

interface PictureRepo {
    suspend fun loadPictures(number_of_pictures:Int): PhotoList
    suspend fun loadPicturesWithoutCash(number_of_pictures:Int): PhotoList
}