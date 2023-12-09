package com.example.travelhelper.domain.repository

import com.example.travelhelper.domain.entity.DestinationImage
import com.example.travelhelper.domain.entity.NearbyDestination
import com.example.travelhelper.domain.entity.PopularDestination

interface HomeRepository {
    suspend fun getPopularDestination(startDate: String, endDate: String): List<PopularDestination>
    suspend fun getNearbyDestination(x: String, y: String, radius: String, lan: String): List<NearbyDestination>
    suspend fun getImage(query: String, count: Int): List<DestinationImage>
}