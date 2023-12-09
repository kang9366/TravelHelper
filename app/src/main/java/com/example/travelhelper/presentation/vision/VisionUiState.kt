package com.example.travelhelper.presentation.vision

sealed interface VisionUiState {
    object Loading: VisionUiState
    object Empty : VisionUiState
    data class VisionResult(
        val result: String
    ): VisionUiState
}