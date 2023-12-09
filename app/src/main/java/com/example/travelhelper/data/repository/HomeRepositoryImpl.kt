package com.example.travelhelper.data.repository

import com.example.travelhelper.data.api.ImageApiService
import com.example.travelhelper.data.api.TourApiService
import com.example.travelhelper.data.mapper.toData
import com.example.travelhelper.domain.entity.DestinationImage
import com.example.travelhelper.domain.entity.NearbyDestination
import com.example.travelhelper.domain.entity.PopularDestination
import com.example.travelhelper.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val tourApi: TourApiService,
    private val imageApi: ImageApiService
): HomeRepository {
    override suspend fun getPopularDestination(startDate: String, endDate: String): List<PopularDestination> {
        return tourApi.getPopularDestination(startDate = startDate, endDate = endDate)
            .response
            .body
            .items
            .item
            .map { it.toData() }
    }

    override suspend fun getNearbyDestination(x: String, y: String, radius: String, lan: String): List<NearbyDestination> {
        return tourApi.getNearbyDestination(x = x, y = y, radius = radius, lan = lan)
            .response
            .body
            .items
            .item
            .map { it.toData() }
    }

    override suspend fun getImage(query: String, count: Int): List<DestinationImage> {
        return imageApi.getImage(query = query, count = count).items.map {
            it.toData()
        }
    }
}