package com.example.travelhelper.domain.repository

import com.example.travelhelper.data.model.DestinationDetail

interface DetailRepository {
    suspend fun getDestinationDetail(query: String): DestinationDetail
}