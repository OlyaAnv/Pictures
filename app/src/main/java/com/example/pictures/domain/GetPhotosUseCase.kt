package com.example.pictures.domain

class GetPhotosUseCase(private val photoListRepository: PhotoListRepository) {

    suspend fun getPhotos(numberOfPictures:Int): List<Photo> {
        return photoListRepository.getPhotos(numberOfPictures)
    }
}