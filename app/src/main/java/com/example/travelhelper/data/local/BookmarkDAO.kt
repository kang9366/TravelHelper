package com.example.travelhelper.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookmarkDAO {
    @Query("SELECT * FROM bookmark_table")
    suspend fun getAll(): List<BookmarkEntity>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun saveBookmark(bookmarkEntity: BookmarkEntity)

    @Query("DELETE FROM bookmark_table WHERE id = :id")
    suspend fun deleteBookmark(id: Int)

    @Query("DELETE FROM bookmark_table")
    suspend fun clearBookmark()
}