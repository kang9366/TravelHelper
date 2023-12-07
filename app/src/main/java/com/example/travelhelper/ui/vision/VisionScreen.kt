package com.example.travelhelper.ui.vision

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.travelhelper.R
import com.example.travelhelper.ui.designsystem.component.TopAppBarNavigationType
import com.example.travelhelper.ui.designsystem.component.TravelHelperTopBar
import com.example.travelhelper.ui.designsystem.theme.TravelHelperTheme
import timber.log.Timber

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VisionScreen(
    imageUri: String,
    onBackClick: () -> Unit,
    viewModel: VisionViewModel = hiltViewModel()
) {
    LaunchedEffect(imageUri) {
        viewModel.initImageUri(imageUri.toUri())
        Timber.d("init uri")
    }

    val analysisResult = viewModel.imageAnalysisResult.collectAsState()
    Timber.d(analysisResult.value)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TravelHelperTopBar(
                navigationType = TopAppBarNavigationType.BACK,
                onNavigationClick = onBackClick
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(horizontal = 15.dp, vertical = 10.dp)
                    .clip(MaterialTheme.shapes.large)
            ) {
//                imageBitmap?.let { bitmap ->
//                    Image(bitmap = bitmap.asImageBitmap(), contentDescription = "Loaded image")
//                }
            }
            Text(
                "Search Screen",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }
    }
}

@Preview
@Composable
private fun test() {
    TravelHelperTheme {
        VisionScreen("", {})
    }
}

