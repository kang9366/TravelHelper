package com.example.travelhelper.domain.repository

import com.example.travelhelper.domain.entity.DestinationDetail

interface DetailRepository {
    suspend fun getDestinationDetail(query: String): DestinationDetail
}