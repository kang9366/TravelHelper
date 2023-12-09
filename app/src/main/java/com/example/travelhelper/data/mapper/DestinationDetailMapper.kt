package com.example.travelhelper.data.mapper

import com.example.travelhelper.data.api.model.TourDetailItem
import com.example.travelhelper.domain.entity.DestinationDetail

fun TourDetailItem.toData(): DestinationDetail {
    return DestinationDetail(
        name = title,
        address = "$addr1 $addr2",
        tel = tel,
        x = mapx,
        y = mapy
    )
}