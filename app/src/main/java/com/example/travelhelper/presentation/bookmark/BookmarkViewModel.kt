package com.example.travelhelper.presentation.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelhelper.data.local.BookmarkDatabase
import com.example.travelhelper.data.local.BookmarkEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkDatabase: BookmarkDatabase
): ViewModel() {
    private val _bookmarkList = MutableStateFlow<List<BookmarkEntity>>(emptyList())
    val bookmarkList: StateFlow<List<BookmarkEntity>> = _bookmarkList

    init {
        loadBookmarks()
    }

    private fun loadBookmarks() {
        viewModelScope.launch {
            _bookmarkList.value = bookmarkDatabase.bookmarkDao().getAll()
        }
    }

    fun deleteBookmark(id: Int) {
        viewModelScope.launch {
            bookmarkDatabase.bookmarkDao().deleteBookmark(id)
            loadBookmarks()
        }
    }

    fun clearBookmark() {
        viewModelScope.launch {
            bookmarkDatabase.bookmarkDao().clearBookmark()
            loadBookmarks()
        }
    }
}