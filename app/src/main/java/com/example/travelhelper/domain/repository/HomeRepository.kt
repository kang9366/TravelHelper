package com.example.travelhelper.domain.repository

import com.example.travelhelper.data.model.DestinationImage
import com.example.travelhelper.data.model.NearbyDestination
import com.example.travelhelper.data.model.PopularDestination

interface HomeRepository {
    suspend fun getPopularDestination(startDate: String, endDate: String): List<PopularDestination>
    suspend fun getNearbyDestination(x: String, y: String, radius: String, lan: String): List<NearbyDestination>
    suspend fun getImage(query: String, count: Int): List<DestinationImage>
}