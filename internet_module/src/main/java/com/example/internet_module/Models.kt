package com.example.internet_module

data class PhotoList(
    val photos: List<Photo>
)

data class Photo(
    val title: String,
    val url: String
)