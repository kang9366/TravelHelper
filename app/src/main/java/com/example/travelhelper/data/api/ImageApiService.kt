package com.example.travelhelper.data.api

import com.example.travelhelper.data.api.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageApiService {
    @GET("/v1/search/image")
    suspend fun getImage(
        @Header("X-Naver-Client-Id") clientId: String = "BMk6YpR1qutw6ThNuBiV",
        @Header("X-Naver-Client-Secret") clientSecret: String = "B0xBN1H8qL",
        @Query("query") query: String,
        @Query("sort") sort: String = "sim",
        @Query("display") count: Int
    ): ImageResponse
}