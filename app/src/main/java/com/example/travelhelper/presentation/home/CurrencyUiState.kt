package com.example.travelhelper.presentation.home

import com.example.travelhelper.domain.entity.Currency

sealed interface CurrencyUiState {
    object Loading: CurrencyUiState
    object Empty: CurrencyUiState
    data class CurrencyData(
        val data: List<Currency>
    ): CurrencyUiState
}