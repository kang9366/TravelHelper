package com.example.travelhelper.data.repository

import com.example.travelhelper.data.api.ApiService
import com.example.travelhelper.data.api.model.NearbyDestinationItem
import com.example.travelhelper.data.api.model.PopularDestinationItem
import com.example.travelhelper.data.api.model.TourApiResponse
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: ApiService
): HomeRepository {
    override suspend fun getPopularDestination(startDate: String, endDate: String): TourApiResponse<PopularDestinationItem> {
        return api.getPopularDestination(startDate = startDate, endDate = endDate)
    }

    override suspend fun getNearbyDestination(x: String, y: String, radius: String, lan: String): TourApiResponse<NearbyDestinationItem> {
        return api.getNearbyDestination(x = x, y = y, radius = radius, lan = lan)
    }
}