package com.example.travelhelper.data.remote

import com.example.travelhelper.BuildConfig
import com.example.travelhelper.data.remote.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ImageApiService {
    @GET("/v1/search/image")
    suspend fun getImage(
        @Header("X-Naver-Client-Id") clientId: String = BuildConfig.NAVER_CLIENT_ID,
        @Header("X-Naver-Client-Secret") clientSecret: String = BuildConfig.NAVER_CLIENT_SECRET,
        @Query("query") query: String,
        @Query("sort") sort: String = "sim",
        @Query("display") count: Int
    ): ImageResponse
}