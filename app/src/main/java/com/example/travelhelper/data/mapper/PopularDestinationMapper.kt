package com.example.travelhelper.data.mapper

import com.example.travelhelper.data.remote.model.PopularDestinationItem
import com.example.travelhelper.domain.entity.PopularDestination

fun PopularDestinationItem.toData(): PopularDestination {
    return PopularDestination(
        name = signguNm,
        num = touNum.toFloat().toInt()
    )
}