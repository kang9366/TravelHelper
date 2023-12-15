package com.example.travelhelper.di

import android.content.Context
import androidx.room.Room
import com.example.travelhelper.data.local.BookmarkDAO
import com.example.travelhelper.data.local.BookmarkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BookmarkDatabase {
        return Room.databaseBuilder(
            context,
            BookmarkDatabase::class.java,
            "bookmark_database"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideBookmarkDao(database: BookmarkDatabase): BookmarkDAO {
        return database.bookmarkDao()
    }
}