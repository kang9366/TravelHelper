package com.example.travelhelper.ui.vision

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelhelper.domain.VisionUseCase
import com.google.firebase.Firebase
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.functions
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.cloud.FirebaseVisionCloudDetectorOptions
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VisionViewModel
@Inject constructor(
    private val visionUseCase: VisionUseCase
): ViewModel() {
    private val _imageUri = MutableStateFlow<Uri?>(null)
    val imageAnalysisResult: StateFlow<String> = _imageUri
        .filterNotNull() // null이 아닌 URI만 처리
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