package com.example.travelhelper.data.remote.model

data class NearbyDestinationItem(
    val tid: Int,
    val tlid: Int,
    val themeCategory: String?,
    val addr1: String,
    val addr2: String?,
    val title: String,
    val mapX: Float,
    val mapY: Float,
    val langCheck: String,
    val langCode: String,
    val imageUrl: String?,
    val createdtime: String,
    val modifiedtime: String
)
