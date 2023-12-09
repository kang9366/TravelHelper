package com.example.travelhelper.presentation.home

import com.example.travelhelper.domain.entity.NearbyDestination

sealed interface NearbyDestinationUiState {
    object Loading: NearbyDestinationUiState
    object Empty: NearbyDestinationUiState
    data class NearbyDestinations(
        val nearbyDestinations: List<NearbyDestination>,
        val imageList: List<List<String>>
    ): NearbyDestinationUiState
}