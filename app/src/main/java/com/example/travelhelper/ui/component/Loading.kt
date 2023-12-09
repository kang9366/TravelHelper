package com.example.travelhelper.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Loading(modifier: Modifier) {
    Row(
        modifier = modifier.size(25.dp)
    ) {
        CircularProgressIndicator()
    }
}