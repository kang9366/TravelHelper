package com.example.travelhelper.data.mapper

import com.example.travelhelper.data.remote.model.ImageItem
import com.example.travelhelper.domain.entity.DestinationImage

fun ImageItem.toData(): DestinationImage {
    return DestinationImage(
        imageUrl = link
    )
}