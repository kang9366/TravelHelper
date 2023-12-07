package com.example.travelhelper.ui.vision

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelhelper.domain.usecase.VisionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class VisionViewModel
@Inject constructor(
    private val visionUseCase: VisionUseCase
): ViewModel() {
    private val _imageUri = MutableStateFlow<Uri?>(null)
    @OptIn(ExperimentalCoroutinesApi::class)
    val imageAnalysisResult: StateFlow<String> = _imageUri
        .filterNotNull()
        .flatMapLatest { uri ->
            flow {
                val result = visionUseCase(uri) // VisionUseCase 호출
                emit(result) // 결과 발행
            }
        }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            "" // 초기값
        )

    fun initImageUri(uri: Uri) {
        _imageUri.value = uri
    }
}