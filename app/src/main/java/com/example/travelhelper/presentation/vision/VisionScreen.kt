package com.example.travelhelper.presentation.vision

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelhelper.ui.component.AlertDialog
import com.example.travelhelper.ui.component.Loading
import com.example.travelhelper.ui.component.TopAppBarNavigationType
import com.example.travelhelper.ui.component.TravelHelperTopBar
import com.example.travelhelper.ui.theme.TravelHelperTheme
import com.example.travelhelper.presentation.detail.DetailContent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VisionScreen(
    imageUri: String,
    onBackClick: () -> Unit,
    viewModel: VisionViewModel = hiltViewModel(),
    callTel: (String) -> Unit
) {
    val visionUiState by viewModel.visionUiState.collectAsState()
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchVisionResult(imageUri.toUri())
    }

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
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            when(visionUiState) {
                is VisionUiState.Loading -> {
                    Loading(modifier = Modifier.align(Alignment.CenterHorizontally))
                }
                is VisionUiState.Empty -> {
                    AlertDialog(onDismissRequest = onBackClick) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(230.dp)
                                .padding(horizontal = 10.dp)
                                .clip(RoundedCornerShape(16.dp))
                                .background(Color.White),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Spacer(modifier = Modifier.height(85.dp))
                            Text(
                                text = "No matching result",
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                onClick = onBackClick
                            ) {
                                Text(
                                    text = "OK",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            }
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
                is VisionUiState.VisionResult -> {
                    FetchVisionDetail(
                        viewModel = viewModel,
                        destination = (visionUiState as VisionUiState.VisionResult).result,
                        onBackClick = onBackClick,
                        callTel = callTel
                    )
                }
            }
        }
    }
}

@Composable
private fun FetchVisionDetail(viewModel: VisionViewModel, destination: String, onBackClick: () -> Unit, callTel: (String) -> Unit) {
    val destinationDetailUiState by viewModel.visionDetailUiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchVisionDetail(destination)
    }
    DetailContent(uiState = destinationDetailUiState, onBackClick = onBackClick, callTel = { callTel(it) })
}

@Preview
@Composable
private fun test() {
    TravelHelperTheme {
//        VisionScreen("", {})
    }
}

