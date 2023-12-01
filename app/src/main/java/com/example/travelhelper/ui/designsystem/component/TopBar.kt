package com.example.travelhelper.ui.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

enum class TopAppBarNavigationType { BACK, HOME, BOOKMARK }

@Composable
fun TravelHelperTopBar(
    navigationType: TopAppBarNavigationType
) {
    Box(
        modifier = Modifier.fillMaxWidth().height(48.dp).background(Color.White)
    ) {
        when(navigationType) {
            TopAppBarNavigationType.BACK -> {

            }
            TopAppBarNavigationType.BOOKMARK -> {

            }
            TopAppBarNavigationType.HOME -> {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}