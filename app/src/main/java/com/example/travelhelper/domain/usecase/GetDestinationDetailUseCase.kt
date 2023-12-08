package com.example.travelhelper.domain.usecase

import com.example.travelhelper.data.model.DestinationDetail
import com.example.travelhelper.domain.repository.DetailRepository
import javax.inject.Inject

class GetDestinationDetailUseCase @Inject constructor(
    private val detailRepository: DetailRepository
): UseCase {
    suspend operator fun invoke(query: String): DestinationDetail {
        return detailRepository.getDestinationDetail(query)
    }
}