package com.example.travelhelper.domain.usecase

import com.example.travelhelper.data.model.NearbyDestination
import com.example.travelhelper.domain.repository.HomeRepository
import javax.inject.Inject

class GetNearbyDestinationUseCase @Inject constructor(
    private val homeRepository: HomeRepository
): UseCase {
    suspend operator fun invoke(x: String, y: String, radius: String, lan: String): List<NearbyDestination> {
        return homeRepository.getNearbyDestination(x, y, radius, lan)
    }
}