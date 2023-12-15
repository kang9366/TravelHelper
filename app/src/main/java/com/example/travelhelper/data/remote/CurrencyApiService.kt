package com.example.travelhelper.data.remote

import com.example.travelhelper.BuildConfig
import com.example.travelhelper.data.remote.model.CurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApiService {
    @GET("/site/program/financial/exchangeJSON")
    suspend fun getCurrency(
        @Query("authkey") apiKey: String = BuildConfig.CURRENCY_API_KEY,
        @Query("searchdate") date: String = "20231214",
        @Query("data") type: String = "AP01"
    ): List<CurrencyResponse>
}