package com.example.pictures.presentation.model

data class PhotoListUI(
    val photos: List<PhotoUI>
)

data class PhotoUI(
    val title: String,
    val url: String
)