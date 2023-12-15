package com.example.travelhelper.data.mapper

import com.example.travelhelper.data.remote.model.NearbyDestinationItem
import com.example.travelhelper.domain.entity.NearbyDestination

fun NearbyDestinationItem.toData(): NearbyDestination {
    return NearbyDestination(
        name = title,
        address = "$addr1 $addr2",
        x = mapX,
        y = mapY
    )
}