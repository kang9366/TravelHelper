package com.example.travelhelper.data.repository

import com.example.travelhelper.data.remote.GptApiService
import com.example.travelhelper.data.remote.TourApiService
import com.example.travelhelper.data.remote.model.ChatGptRequest
import com.example.travelhelper.data.remote.model.GptResponse
import com.example.travelhelper.data.mapper.toData
import com.example.travelhelper.domain.entity.DestinationDetail
import com.example.travelhelper.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val tourApi: TourApiService,
    private val gptApi: GptApiService
): DetailRepository {
    override suspend fun getDestinationDetail(query: String): DestinationDetail {
        return tourApi.getDestinationDetail(query = query)
            .response
            .body
            .items
            .item[0]
            .toData()
    }

    override suspend fun getDestinationInformation(request: ChatGptRequest): GptResponse {
        return gptApi.getDestinationInformation(request = request)
    }
}