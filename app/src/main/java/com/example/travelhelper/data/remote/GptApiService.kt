package com.example.travelhelper.data.remote

import com.example.travelhelper.BuildConfig
import com.example.travelhelper.data.remote.model.ChatGptRequest
import com.example.travelhelper.data.remote.model.GptResponse
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface GptApiService {
    @Headers("Content-Type: application/json")
    @POST("/v1/chat/completions")
    suspend fun getDestinationInformation(
        @Header("Authorization") token: String = "Bearer ${BuildConfig.GPT_API_KEY}",
        @Body request: ChatGptRequest
    ): GptResponse
}