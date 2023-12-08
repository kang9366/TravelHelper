package com.example.travelhelper.data.mapper

import com.example.travelhelper.data.api.model.PopularDestinationItem
import com.example.travelhelper.data.model.PopularDestination

fun PopularDestinationItem.toData(): PopularDestination {
    return PopularDestination(
        name = signguNm,
        num = touNum.toFloat().toInt()
    )
}