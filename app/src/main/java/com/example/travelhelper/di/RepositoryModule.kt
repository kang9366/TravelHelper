package com.example.travelhelper.di

import android.content.ContentResolver
import com.example.travelhelper.data.remote.CurrencyApiService
import com.example.travelhelper.data.remote.GptApiService
import com.example.travelhelper.data.remote.ImageApiService
import com.example.travelhelper.data.remote.TourApiService
import com.example.travelhelper.data.repository.DetailRepositoryImpl
import com.example.travelhelper.domain.repository.HomeRepository
import com.example.travelhelper.data.repository.HomeRepositoryImpl
import com.example.travelhelper.domain.repository.VisionRepository
import com.example.travelhelper.data.repository.VisionRepositoryImpl
import com.example.travelhelper.domain.repository.DetailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideVisionRepository(contentResolver: ContentResolver): VisionRepository {
        return VisionRepositoryImpl(contentResolver)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(tourApiService: TourApiService, imageApiService: ImageApiService, currencyApiService: CurrencyApiService): HomeRepository {
        return HomeRepositoryImpl(tourApiService, imageApiService, currencyApiService)
    }

    @Provides
    @Singleton
    fun provideDetailRepository(tourApiService: TourApiService, gptApiService: GptApiService): DetailRepository {
        return DetailRepositoryImpl(tourApiService, gptApiService)
    }
}