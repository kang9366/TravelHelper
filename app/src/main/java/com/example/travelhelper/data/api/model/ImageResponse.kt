package com.example.travelhelper.data.api.model

data class ImageResponse(
    val lastBuildDate: String,
    val total: Int,
    val start: Int,
    val display: Int,
    val items: List<ImageItem>
)

data class ImageItem(
    val title: String,
    val link: String,
    val thumbnail: String,
    val sizeheight: String,
    val sizewidth: String
)
