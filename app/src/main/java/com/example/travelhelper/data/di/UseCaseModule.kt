package com.example.travelhelper.data.di

import com.example.travelhelper.domain.repository.DetailRepository
import com.example.travelhelper.domain.repository.HomeRepository
import com.example.travelhelper.domain.repository.VisionRepository
import com.example.travelhelper.domain.usecase.GetDestinationDetailUseCase
import com.example.travelhelper.domain.usecase.GetImageUseCase
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

    @Provides
    fun provideGetImageUseCase(homeRepository: HomeRepository): GetImageUseCase {
        return GetImageUseCase(homeRepository)
    }

    @Provides
    fun provideGetDestinationDetailUseCase(detailRepository: DetailRepository): GetDestinationDetailUseCase {
        return GetDestinationDetailUseCase(detailRepository)
    }
}