package com.example.travelhelper.presentation.detail

import com.example.travelhelper.data.model.DestinationDetail

sealed interface DestinationDetailUiState {
    object Loading: DestinationDetailUiState
    object Empty: DestinationDetailUiState
    data class DestinationDetails(
        val destinationDetail: DestinationDetail,
        val image: List<String>
    ): DestinationDetailUiState
}