package com.example.pictures.domain

interface PhotoListRepository {
    suspend fun getPhotos(numberOfPictures:Int): List<Photo>
}