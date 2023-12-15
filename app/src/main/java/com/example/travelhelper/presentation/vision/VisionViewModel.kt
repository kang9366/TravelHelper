package com.example.travelhelper.presentation.vision

import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelhelper.data.remote.model.ChatGptRequest
import com.example.travelhelper.data.remote.model.RequestMessage
import com.example.travelhelper.domain.usecase.GetDestinationDetailUseCase
import com.example.travelhelper.domain.usecase.GetDestinationInformationUseCase
import com.example.travelhelper.domain.usecase.GetImageUseCase
import com.example.travelhelper.domain.usecase.VisionUseCase
import com.example.travelhelper.presentation.detail.DestinationDetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class VisionViewModel
@Inject constructor(
    private val getDestinationDetailUseCase: GetDestinationDetailUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val visionUseCase: VisionUseCase,
    private val getDestinationInformationUseCase: GetDestinationInformationUseCase
): ViewModel() {
    private val _visionUiState = MutableStateFlow<VisionUiState>(VisionUiState.Loading)
    val visionUiState: StateFlow<VisionUiState> = _visionUiState

    private val _visionDetailUiState = MutableStateFlow<DestinationDetailUiState>(DestinationDetailUiState.Loading)
    val visionDetailUiState: StateFlow<DestinationDetailUiState> = _visionDetailUiState

    fun fetchVisionResult(imageUri: Uri) {
        viewModelScope.launch {
            _visionUiState.value = VisionUiState.Loading
            try {
                val response = visionUseCase(imageUri)
                Log.d("testtsxz", response.toString())
                if(response.isBlank()) {
                    _visionUiState.value = VisionUiState.Empty
                } else {
                    _visionUiState.value = VisionUiState.VisionResult(response)
                }
            } catch (e: Exception) {
                Timber.d(e.message.toString())
            }
        }
    }

    fun fetchVisionDetail(query: String) {
        val question = listOf(RequestMessage(content = "let me introduce about $query in 250 characters"))
        val request = ChatGptRequest(messages = question)

        viewModelScope.launch {
            _visionDetailUiState.value = DestinationDetailUiState.Loading
            try {
                val destinationDetailResponse = getDestinationDetailUseCase(query)
                val imageResponse = async {
                    getImageUseCase(query, 5).map { it.imageUrl }
                }
                val gptResponse = async {
                    getDestinationInformationUseCase(request)
                }
                _visionDetailUiState.value = DestinationDetailUiState.DestinationDetails(destinationDetailResponse, imageResponse.await(), gptResponse.await())
            } catch (e: Exception) {
                _visionDetailUiState.value = DestinationDetailUiState.Empty
            }
        }
    }
}