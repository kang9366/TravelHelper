package com.example.travelhelper.data.repository

import com.example.travelhelper.data.api.model.NearbyDestinationItem
import com.example.travelhelper.data.api.model.PopularDestinationItem
import com.example.travelhelper.data.api.model.TourApiResponse


interface HomeRepository {
    suspend fun getPopularDestination(startDate: String, endDate: String): TourApiResponse<PopularDestinationItem>
    suspend fun getNearbyDestination(x: String, y: String, radius: String, lan: String): TourApiResponse<NearbyDestinationItem>
}