package com.example.pictures.data.model

data class PhotoDtoList(
    val photos: List<PhotoDto>
)

data class PhotoDto(
    val title: String,
    val url: String
)