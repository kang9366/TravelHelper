package com.example.travelhelper.domain.usecase

import com.example.travelhelper.data.model.DestinationImage
import com.example.travelhelper.domain.repository.HomeRepository
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val homeRepository: HomeRepository
): UseCase {
    suspend operator fun invoke(query: String, count: Int): List<DestinationImage> {
        return homeRepository.getImage(query = query, count = count)
    }
}