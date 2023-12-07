package com.example.travelhelper.domain.usecase

import android.net.Uri
import com.example.travelhelper.data.api.model.PopularDestinationItem
import com.example.travelhelper.data.api.model.TourApiResponse
import com.example.travelhelper.data.repository.HomeRepository
import javax.inject.Inject

class GetPopularDestinationUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) {
    suspend operator fun invoke(startDate: String, endDate: String): TourApiResponse<PopularDestinationItem> {
        return homeRepository.getPopularDestination(startDate, endDate)
    }
}