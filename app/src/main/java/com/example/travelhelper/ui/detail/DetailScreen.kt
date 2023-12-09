package com.example.travelhelper.ui.detail

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelhelper.R
import com.example.travelhelper.ui.designsystem.component.AlertDialog
import com.example.travelhelper.ui.designsystem.component.ImageSlider
import com.example.travelhelper.ui.designsystem.component.Loading
import com.example.travelhelper.ui.designsystem.component.TopAppBarNavigationType
import com.example.travelhelper.ui.designsystem.component.TravelHelperTopBar
import com.example.travelhelper.ui.designsystem.theme.TravelHelperTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    title: String,
    onBackClick: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val destinationDetailUiState by viewModel.destinationDetailUiState.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchDestinationDetail(title)
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
                .padding(horizontal = 15.dp),
        ) {
            Spacer(modifier = Modifier.height(68.dp))

            DetailContent(destinationDetailUiState, onBackClick)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailContent(
    uiState: DestinationDetailUiState,
    onBackClick: () -> Unit
) {
    when(uiState) {
        is DestinationDetailUiState.DestinationDetails -> {
            val location = LatLng(uiState.destinationDetail.y, uiState.destinationDetail.x)
            val cameraPositionState = rememberCameraPositionState {
                position = CameraPosition.fromLatLngZoom(location, 15f)
            }

            Column {
                ImageSlider(
                    imageList = uiState.image
                )
                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = uiState.destinationDetail.name
                )
                Spacer(modifier = Modifier.height(13.dp))
                Information(iconRes = R.drawable.ic_location, description = uiState.destinationDetail.address)
                Spacer(modifier = Modifier.height(13.dp))
                Information(iconRes = R.drawable.ic_tel, description = uiState.destinationDetail.tel)
                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    cameraPositionState = cameraPositionState
                ) {
                    Marker(
                        state = MarkerState(position = location),
                        title = uiState.destinationDetail.name
                    )
                }
            }
        }
        is DestinationDetailUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Loading(modifier = Modifier)
            }
        }
        is DestinationDetailUiState.Empty -> {
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
                        text = "There is no data in the public data portal",
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
    }
}

@Composable
private fun Information(
    @DrawableRes iconRes: Int,
    description: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = Color.Unspecified
        )

        Spacer(modifier = Modifier.width(5.dp))

        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview
@Composable
private fun Test() {
    TravelHelperTheme {
        Information(R.drawable.ic_tel, "test")
    }
}