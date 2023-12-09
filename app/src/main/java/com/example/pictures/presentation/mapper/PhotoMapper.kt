package com.example.pictures.presentation.mapper

import com.example.pictures.domain.Photo
import com.example.pictures.presentation.model.PhotoUI

class PhotoMapper {
        private fun mapDataModelToUiModel(photo: Photo) = PhotoUI(
            title = photo.title,
            url = photo.url
        )

    fun mapListDataModelToListUiModel(list: List<Photo>) = list.map {
        mapDataModelToUiModel(it)
    }
}