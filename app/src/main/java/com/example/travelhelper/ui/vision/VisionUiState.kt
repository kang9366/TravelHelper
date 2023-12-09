package com.example.travelhelper.ui.vision

sealed interface VisionUiState {
    object Loading: VisionUiState
    object Empty : VisionUiState
    data class VisionResult(
        val result: String
    ): VisionUiState
}