package com.example.travelhelper.presentation.home

import com.example.travelhelper.domain.entity.PopularDestination


sealed interface PopularDestinationUiState {
    object Loading: PopularDestinationUiState
    object Empty: PopularDestinationUiState
    data class PopularDestinations(
        val popularDestination: List<PopularDestination>,
        val imageList: List<List<String>>
    ): PopularDestinationUiState
}