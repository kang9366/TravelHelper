package com.example.travelhelper.data.di

import com.example.travelhelper.data.repository.VisionRepository
import com.example.travelhelper.domain.VisionUseCase
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
}