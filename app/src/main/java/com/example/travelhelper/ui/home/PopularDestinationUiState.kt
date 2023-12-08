package com.example.travelhelper.ui.home

import com.example.travelhelper.data.model.PopularDestination


sealed interface PopularDestinationUiState {
    object Loading: PopularDestinationUiState
    object Empty: PopularDestinationUiState
    data class PopularDestinations(
        val popularDestination: List<PopularDestination>,
        val imageList: List<List<String>>
    ): PopularDestinationUiState
}