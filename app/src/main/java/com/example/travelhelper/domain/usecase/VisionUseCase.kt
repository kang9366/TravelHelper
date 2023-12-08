package com.example.travelhelper.domain.usecase

import android.net.Uri
import com.example.travelhelper.domain.repository.VisionRepository
import javax.inject.Inject

class VisionUseCase @Inject constructor(
    private val visionRepository: VisionRepository
): UseCase {
    suspend operator fun invoke(imageUri: Uri): String {
        return visionRepository.getLandmarkInfo(imageUri)
    }
}