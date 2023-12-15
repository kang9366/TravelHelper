package com.example.travelhelper.domain.repository

import com.example.travelhelper.data.remote.model.ChatGptRequest
import com.example.travelhelper.data.remote.model.GptResponse
import com.example.travelhelper.domain.entity.DestinationDetail

interface DetailRepository {
    suspend fun getDestinationDetail(query: String): DestinationDetail
    suspend fun getDestinationInformation(request: ChatGptRequest): GptResponse
}