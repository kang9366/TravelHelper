package com.example.travelhelper.di

import com.example.travelhelper.data.remote.CurrencyApiService
import com.example.travelhelper.data.remote.GptApiService
import com.example.travelhelper.data.remote.ImageApiService
import com.example.travelhelper.data.remote.TourApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ApiModule {
    private const val TOUR_BASE_URL = "https://apis.data.go.kr"
    private const val IMAGE_BASE_URL = "https://openapi.naver.com"
    private const val GPT_BASE_URL = "https://api.openai.com"
    private const val CURRENCY_BASE_URL = "https://www.koreaexim.go.kr"

    @Provides
    @Singleton
    fun provideTourApiService(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): TourApiService {
        return Retrofit.Builder()
            .baseUrl(TOUR_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient).build()
            .create(TourApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .build()
    }



    @Provides
    @Singleton
    fun provideImageApiService(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): ImageApiService {
        return Retrofit.Builder()
            .baseUrl(IMAGE_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient).build()
            .create(ImageApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideGPTApiService(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): GptApiService{
        return Retrofit.Builder()
            .baseUrl(GPT_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient).build()
            .create(GptApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyApiService(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): CurrencyApiService {
        return Retrofit.Builder()
            .baseUrl(CURRENCY_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient).build()
            .create(CurrencyApiService::class.java)
    }
}