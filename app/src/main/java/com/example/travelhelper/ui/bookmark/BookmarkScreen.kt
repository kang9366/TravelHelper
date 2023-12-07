package com.example.travelhelper.ui.bookmark

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.travelhelper.ui.designsystem.component.BookmarkContent
import com.example.travelhelper.ui.designsystem.component.TopAppBarNavigationType
import com.example.travelhelper.ui.designsystem.component.TravelHelperTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookmarkScreen(navController: NavController) {
    Scaffold(
        topBar = { TravelHelperTopBar(navigationType = TopAppBarNavigationType.BOOKMARK) },
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp)
                .background(Color(0xFFF5F7FC))
        ) {
            items(100) {
                Spacer(modifier = Modifier.height(16.dp))
                BookmarkContent(
                    onClick = {

                    }
                )
            }
        }
    }
}