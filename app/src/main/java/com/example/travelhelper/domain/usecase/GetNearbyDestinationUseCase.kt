package com.example.travelhelper.domain.usecase

import com.example.travelhelper.data.api.model.NearbyDestinationItem
import com.example.travelhelper.data.api.model.TourApiResponse
import com.example.travelhelper.data.repository.HomeRepository
import javax.inject.Inject

class GetNearbyDestinationUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(x: String, y: String, radius: String, lan: String): TourApiResponse<NearbyDestinationItem> {
        return homeRepository.getNearbyDestination(x, y, radius, lan)
    }
}