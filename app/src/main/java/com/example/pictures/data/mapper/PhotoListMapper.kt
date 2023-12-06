package com.example.pictures.data.mapper

import com.example.pictures.data.model.PhotoDto
import com.example.pictures.domain.Photo

class PhotoListMapper {
    private fun mapNetworkModelToEntity(photoDto: PhotoDto) = Photo(
        title = photoDto.title,
        url = photoDto.url
    )

    fun mapListNetworkModelToListEntity(list: List<PhotoDto>) = list.map {
        mapNetworkModelToEntity(it)
    }
}