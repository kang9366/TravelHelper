package com.example.travelhelper.ui.home

import com.example.travelhelper.data.model.NearbyDestination

sealed interface NearbyDestinationUiState {
    object Loading: NearbyDestinationUiState
    object Empty: NearbyDestinationUiState
    data class NearbyDestinations(
        val nearbyDestinations: List<NearbyDestination>,
        val imageList: List<List<String>>
    ): NearbyDestinationUiState
}