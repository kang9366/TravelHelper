package com.example.travelhelper.presentation.bookmark

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelhelper.ui.component.BookmarkContent
import com.example.travelhelper.ui.component.TopAppBarNavigationType
import com.example.travelhelper.ui.component.TravelHelperTopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookmarkScreen(
    navigateToDetail: (String) -> Unit,
    viewModel: BookmarkViewModel = hiltViewModel()
) {
    val bookmarkList by viewModel.bookmarkList.collectAsState()

    Scaffold(
        topBar = {
            TravelHelperTopBar(
                navigationType = TopAppBarNavigationType.BOOKMARK,
                onNavigationClick = { viewModel.clearBookmark() }
            )
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 63.dp)
                .background(Color(0xFFF5F7FC)),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(bookmarkList) {item ->
                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if (it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                            viewModel.deleteBookmark(item.id)
                            true
                        } else {
                            false
                        }
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    directions = setOf(DismissDirection.EndToStart),
                    background = {
                        val backgroundColor = if (dismissState.dismissDirection == DismissDirection.EndToStart) {
                            MaterialTheme.colorScheme.error
                        } else {
                            Color.Transparent
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(133.dp)
                                .background(backgroundColor)
                                .padding(horizontal = 20.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Delete",
                                tint = Color.White
                            )
                        }
                    },
                    dismissContent = {
                        BookmarkContent(
                            item = item,
                            onClick = { navigateToDetail(item.name) }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                    }
                )
            }
        }
    }
}
