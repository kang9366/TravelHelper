package com.example.travelhelper.presentation.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelhelper.data.local.BookmarkDatabase
import com.example.travelhelper.data.local.BookmarkEntity
import com.example.travelhelper.data.remote.model.ChatGptRequest
import com.example.travelhelper.data.remote.model.RequestMessage
import com.example.travelhelper.domain.usecase.GetDestinationDetailUseCase
import com.example.travelhelper.domain.usecase.GetDestinationInformationUseCase
import com.example.travelhelper.domain.usecase.GetImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDestinationDetailUseCase: GetDestinationDetailUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val getDestinationInformationUseCase: GetDestinationInformationUseCase,
    private val bookmarkDatabase: BookmarkDatabase
): ViewModel() {
    private val _destinationDetailUiState = MutableStateFlow<DestinationDetailUiState>(DestinationDetailUiState.Loading)
    val destinationDetailUiState: StateFlow<DestinationDetailUiState> = _destinationDetailUiState

    private val _errorState = MutableStateFlow<Boolean?>(null)
    val errorState: StateFlow<Boolean?> = _errorState

    fun fetchDestinationDetail(query: String) {
        val question = listOf(RequestMessage(content = "let me introduce about $query in 250 characters"))
        val request = ChatGptRequest(messages = question)

        viewModelScope.launch {
            _destinationDetailUiState.value = DestinationDetailUiState.Loading
            try {
                val destinationDetailResponse = getDestinationDetailUseCase(query)

                val imageResponse = async {
                    getImageUseCase(query, 5).map { it.imageUrl }
                }
                val gptResponse = async {
                    getDestinationInformationUseCase(request)
                }
                _destinationDetailUiState.value = DestinationDetailUiState.DestinationDetails(destinationDetailResponse, imageResponse.await(), gptResponse.await())
            } catch (e: Exception) {
                _destinationDetailUiState.value = DestinationDetailUiState.Empty
            }
        }
    }

    fun saveToBookmarkList(name: String, location: String, imageUrl: String) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    bookmarkDatabase.bookmarkDao().saveBookmark(BookmarkEntity(name = name, location = location, imageUrl = imageUrl))
                }
                _errorState.value = false
            } catch (e: Exception) {
                _errorState.value = true
            }
            val allBookmarks = withContext(Dispatchers.IO) {
                bookmarkDatabase.bookmarkDao().getAll()
            }
            Log.d("tesaw", allBookmarks.toString())
        }
    }
}