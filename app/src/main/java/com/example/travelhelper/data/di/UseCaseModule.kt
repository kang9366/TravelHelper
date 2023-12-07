package com.example.travelhelper.data.di

import com.example.travelhelper.data.repository.HomeRepository
import com.example.travelhelper.data.repository.VisionRepository
import com.example.travelhelper.domain.usecase.GetNearbyDestinationUseCase
import com.example.travelhelper.domain.usecase.GetPopularDestinationUseCase
import com.example.travelhelper.domain.usecase.VisionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    fun provideVisionUseCase(visionRepository: VisionRepository): VisionUseCase {
        return VisionUseCase(visionRepository)
    }

    @Provides
    fun provideGetPopularDestinationUseCase(homeRepository: HomeRepository): GetPopularDestinationUseCase {
        return GetPopularDestinationUseCase(homeRepository)
    }

    @Provides
    fun provideGetNearbyDestinationUseCase(homeRepository: HomeRepository): GetNearbyDestinationUseCase {
        return GetNearbyDestinationUseCase(homeRepository)
    }
}