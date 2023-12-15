package com.example.travelhelper.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelhelper.domain.usecase.GetCurrencyUseCase
import com.example.travelhelper.domain.usecase.GetImageUseCase
import com.example.travelhelper.domain.usecase.GetNearbyDestinationUseCase
import com.example.travelhelper.domain.usecase.GetPopularDestinationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@SuppressLint("LogNotTimber")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPopularDestinationUseCase: GetPopularDestinationUseCase,
    private val getNearbyDestinationUseCase: GetNearbyDestinationUseCase,
    private val getImageUseCase: GetImageUseCase,
    private val getCurrencyUseCase: GetCurrencyUseCase
): ViewModel() {
    private val _popularDestinationUiState = MutableStateFlow<PopularDestinationUiState>(PopularDestinationUiState.Loading)
    val popularDestinationUiState: StateFlow<PopularDestinationUiState> = _popularDestinationUiState

    private val _nearbyDestinationUiState = MutableStateFlow<NearbyDestinationUiState>(NearbyDestinationUiState.Loading)
    val nearbyDestinationUiState: StateFlow<NearbyDestinationUiState> = _nearbyDestinationUiState

    private val _currencyUiState = MutableStateFlow<CurrencyUiState>(CurrencyUiState.Loading)
    val currencyUiState: StateFlow<CurrencyUiState> = _currencyUiState

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchCurrencyData() {
        viewModelScope.launch {
            _currencyUiState.value = CurrencyUiState.Loading
            try {
                val currencyResponse = getCurrencyUseCase()
                if(currencyResponse.isEmpty()) {
                    _currencyUiState.value = CurrencyUiState.Empty
                } else {
                    _currencyUiState.value = CurrencyUiState.CurrencyData(currencyResponse)
                }
            } catch (e: Exception) {
                Timber.d(e.message.toString())
            }
        }
    }

    fun fetchPopularDestinations(startDate: String, endDate: String) {
        viewModelScope.launch {
            _popularDestinationUiState.value = PopularDestinationUiState.Loading
            try {
                val popularDestinationResponse = getPopularDestinationUseCase(startDate, endDate)
                if(popularDestinationResponse.isEmpty()) {
                    _popularDestinationUiState.value = PopularDestinationUiState.Empty
                } else {
                    val imageResponse = async {
                        List(popularDestinationResponse.size) {
                            getImageUseCase(popularDestinationResponse[it].name + "풍경", 1)
                        }.map { destinationImages ->
                            destinationImages.map { it.imageUrl }
                        }
                    }
                    _popularDestinationUiState.value = PopularDestinationUiState.PopularDestinations(popularDestinationResponse, imageResponse.await())
                    Log.d("testsw", _popularDestinationUiState.value.toString())
                }
            } catch (e: Exception) {
                Timber.d(e.message.toString())
            }
        }
    }

    fun fetchNearbyDestinations(x: String, y: String, radius: String, lan: String) {
        viewModelScope.launch {
            _nearbyDestinationUiState.value = NearbyDestinationUiState.Loading

            try {
                val destinationResponse = getNearbyDestinationUseCase(x, y, radius, lan)
                if (destinationResponse.isEmpty()) {
                    _nearbyDestinationUiState.value = NearbyDestinationUiState.Empty
                } else {
                    val imageResponse = async {
                        List(destinationResponse.size) {
                            getImageUseCase(destinationResponse[it].name + " 풍경", 1)
                        }.map { destinationImages ->
                            destinationImages.map { it.imageUrl }
                        }
                    }

                    Log.d("testts", imageResponse.await().size.toString())

                    _nearbyDestinationUiState.value = NearbyDestinationUiState.NearbyDestinations(destinationResponse, imageResponse.await())
                }
            } catch (e: Exception) {
                Timber.d(e.message.toString())
            }
        }
    }
}