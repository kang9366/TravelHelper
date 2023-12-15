package com.example.travelhelper.domain.usecase

import com.example.travelhelper.data.remote.model.ChatGptRequest
import com.example.travelhelper.domain.repository.DetailRepository
import javax.inject.Inject

class GetDestinationInformationUseCase @Inject constructor(
    private val detailRepository: DetailRepository
): UseCase {
    suspend operator fun invoke(request: ChatGptRequest): String {
        return detailRepository.getDestinationInformation(request).choices[0].message.content
    }
}