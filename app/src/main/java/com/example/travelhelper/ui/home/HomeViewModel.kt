package com.example.travelhelper.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelhelper.data.api.model.PopularDestinationItem
import com.example.travelhelper.data.api.model.TourApiResponse
import com.example.travelhelper.domain.usecase.GetNearbyDestinationUseCase
import com.example.travelhelper.domain.usecase.GetPopularDestinationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularDestinationUseCase: GetPopularDestinationUseCase,
    private val getNearbyDestinationUseCase: GetNearbyDestinationUseCase
): ViewModel() {
    private val _popularDestinations = MutableStateFlow<TourApiResponse<PopularDestinationItem>?>(null)
    val popularDestinations: StateFlow<TourApiResponse<PopularDestinationItem>?> = _popularDestinations

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun loadPopularDestinations(startDate: String, endDate: String) {
        viewModelScope.launch {
            try {
                val response = getPopularDestinationUseCase(startDate, endDate)
                _popularDestinations.value = response
                Timber.d(response.toString())
            } catch (e: Exception) {
                _error.value = e.message ?: "An unknown error occurred"
                Timber.d(e.message.toString())
            }
        }
    }

    fun loadNearbyDestinations(x: String, y: String, radius: String, lan: String) {
        viewModelScope.launch {
            try {
                val response = getNearbyDestinationUseCase(x, y, radius, lan)
                Timber.d(response.toString())
            } catch (e: Exception) {

            }
        }
    }
}