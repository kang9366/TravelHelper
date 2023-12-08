package com.example.travelhelper.domain.usecase

import com.example.travelhelper.data.model.PopularDestination
import com.example.travelhelper.domain.repository.HomeRepository
import javax.inject.Inject

class GetPopularDestinationUseCase @Inject constructor(
    private val homeRepository: HomeRepository
): UseCase {
    suspend operator fun invoke(startDate: String, endDate: String): List<PopularDestination> {
        return homeRepository.getPopularDestination(startDate, endDate)
            .distinctBy { it.name }
    }
}