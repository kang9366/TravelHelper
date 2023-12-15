package com.example.travelhelper.presentation.detail

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelhelper.R
import com.example.travelhelper.ui.component.AlertDialog
import com.example.travelhelper.ui.component.ImageSlider
import com.example.travelhelper.ui.component.Loading
import com.example.travelhelper.ui.component.TopAppBarNavigationType
import com.example.travelhelper.ui.component.TravelHelperTopBar
import com.example.travelhelper.ui.theme.TravelHelperTheme
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
    viewModel: DetailViewModel = hiltViewModel(),
    callTel: (String) -> Unit
) {
    val destinationDetailUiState by viewModel.destinationDetailUiState.collectAsState()
    val errorState by viewModel.errorState.collectAsState()
    var showDialog by remember { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchDestinationDetail(title)
    }

    val address = when (val state = destinationDetailUiState) {
        is DestinationDetailUiState.DestinationDetails -> state.destinationDetail.address
        else -> ""
    }

    val imageUrl = when (val state = destinationDetailUiState) {
        is DestinationDetailUiState.DestinationDetails -> state.image[0]
        else -> ""
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TravelHelperTopBar(
                navigationType = TopAppBarNavigationType.DETAIL,
                onNavigationClick = onBackClick,
                onSaveClick = { viewModel.saveToBookmarkList(title, address, imageUrl) }
            )
        }
    ) {
        errorState?.let {
            if(it) {
                if(showDialog) {
                    AlertDialog(onDismissRequest = { showDialog = false }) {
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
                                text = "You already saved it!",
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .padding(horizontal = 20.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                onClick = { showDialog = false }
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
            }else {
                if(showDialog) {
                    AlertDialog(onDismissRequest = { showDialog = false }) {
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
                                text = "Saved!",
                            )
                            Spacer(modifier = Modifier.weight(1f))
                            Button(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                                    .padding(horizontal = 20.dp)
                                    .clip(RoundedCornerShape(16.dp)),
                                onClick = { showDialog = false }
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 15.dp)
                .verticalScroll(scrollState),
        ) {
            Spacer(modifier = Modifier.height(68.dp))
            DetailContent(destinationDetailUiState, onBackClick, callTel)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailContent(
    uiState: DestinationDetailUiState,
    onBackClick: () -> Unit,
    callTel: (String) -> Unit
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
                Box(
                    modifier = Modifier
                        .clickable(
                            onClick = { callTel(uiState.destinationDetail.tel) }
                        )
                ) {
                    Information(iconRes = R.drawable.ic_tel, description = uiState.destinationDetail.tel)
                }

                Spacer(modifier = Modifier.height(25.dp))
                Text(
                    text = uiState.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(25.dp))
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
                Spacer(modifier = Modifier.height(25.dp))
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
    description: String,
    onClick: (String) -> Unit = { }
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