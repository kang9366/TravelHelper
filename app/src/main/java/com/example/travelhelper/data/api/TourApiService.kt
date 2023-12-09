package com.example.travelhelper.data.api

import com.example.travelhelper.BuildConfig
import com.example.travelhelper.data.api.model.NearbyDestinationItem
import com.example.travelhelper.data.api.model.PopularDestinationItem
import com.example.travelhelper.data.api.model.TourApiResponse
import com.example.travelhelper.data.api.model.TourDetailItem
import retrofit2.http.GET
import retrofit2.http.Query

interface TourApiService {
    @GET("/B551011/DataLabService/locgoRegnVisitrDDList")
    suspend fun getPopularDestination(
        @Query("MobileOS") os: String = "AND",
        @Query("MobileApp") serviceName: String = "TravelHelper",
        @Query("_type") type: String = "json",
        @Query("serviceKey") serviceKey: String = BuildConfig.TOUR_API_KEY,
        @Query("startYmd") startDate: String,
        @Query("endYmd") endDate: String
    ): TourApiResponse<PopularDestinationItem>

    @GET("/B551011/Odii/themeLocationBasedList")
    suspend fun getNearbyDestination(
        @Query("MobileOS") os: String = "AND",
        @Query("MobileApp") serviceName: String = "TravelHelper",
        @Query("_type") type: String = "json",
        @Query("serviceKey") serviceKey: String = BuildConfig.TOUR_API_KEY,
        @Query("mapX") x: String,
        @Query("mapY") y: String,
        @Query("radius") radius: String,
        @Query("langCode") lan: String
    ): TourApiResponse<NearbyDestinationItem>

    @GET("/B551011/EngService1/searchKeyword1")
    suspend fun getDestinationDetail(
        @Query("MobileOS") os: String = "AND",
        @Query("MobileApp") serviceName: String = "TravelHelper",
        @Query("_type") type: String = "json",
        @Query("serviceKey") serviceKey: String = BuildConfig.TOUR_API_KEY,
        @Query("keyword") query: String
    ): TourApiResponse<TourDetailItem>
}