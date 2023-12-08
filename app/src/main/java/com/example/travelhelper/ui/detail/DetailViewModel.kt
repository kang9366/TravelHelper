package com.example.travelhelper.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelhelper.domain.usecase.GetDestinationDetailUseCase
import com.example.travelhelper.domain.usecase.GetImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDestinationDetailUseCase: GetDestinationDetailUseCase,
    private val getImageUseCase: GetImageUseCase
): ViewModel() {
    private val _destinationDetailUiState = MutableStateFlow<DestinationDetailUiState>(DestinationDetailUiState.Loading)
    val destinationDetailUiState: StateFlow<DestinationDetailUiState> = _destinationDetailUiState

    fun fetchDestinationDetail(query: String) {
        viewModelScope.launch {
            _destinationDetailUiState.value = DestinationDetailUiState.Loading
            try {
                val destinationDetailResponse = getDestinationDetailUseCase(query)
                val imageResponse = async {
                    getImageUseCase(query, 5).map { it.imageUrl }
                }
                _destinationDetailUiState.value = DestinationDetailUiState.DestinationDetails(destinationDetailResponse, imageResponse.await())
            } catch (e: Exception) {
                Log.d("testsz", e.message.toString())
            }
        }
    }
}