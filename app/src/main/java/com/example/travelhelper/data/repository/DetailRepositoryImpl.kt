package com.example.travelhelper.data.repository

import com.example.travelhelper.data.api.TourApiService
import com.example.travelhelper.data.mapper.toData
import com.example.travelhelper.data.model.DestinationDetail
import com.example.travelhelper.domain.repository.DetailRepository

class DetailRepositoryImpl(
    private val tourApi: TourApiService
): DetailRepository {
    override suspend fun getDestinationDetail(query: String): DestinationDetail {
        return tourApi.getDestinationDetail(query = query)
            .response
            .body
            .items
            .item[0]
            .toData()
    }
}