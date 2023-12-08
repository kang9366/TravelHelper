package com.example.travelhelper.ui.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelhelper.R
import com.example.travelhelper.ui.designsystem.component.ImageSlider
import com.example.travelhelper.ui.designsystem.component.TopAppBarNavigationType
import com.example.travelhelper.ui.designsystem.component.TravelHelperTopBar
import com.example.travelhelper.ui.designsystem.theme.TravelHelperTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@OptIn(ExperimentalPagerApi::class)
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

            Content(destinationDetailUiState)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun Content(uiState: DestinationDetailUiState) {
    when(uiState) {
        is DestinationDetailUiState.DestinationDetails -> {
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
            }
        }
        is DestinationDetailUiState.Loading -> {
            Text("로딩중")
        }
        is DestinationDetailUiState.Empty -> {
            Text("empty")
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