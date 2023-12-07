package com.example.travelhelper.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelhelper.ui.designsystem.component.PopularityContent
import com.example.travelhelper.ui.designsystem.component.TopAppBarNavigationType
import com.example.travelhelper.ui.designsystem.component.TravelHelperTopBar
import com.example.travelhelper.ui.designsystem.theme.TravelHelperTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    onSearchClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    viewModel.loadPopularDestinations("20231101", "20231105")
    viewModel.loadNearbyDestinations("127.077910", "37.631821", "20000", "ko")
    val scrollState = rememberScrollState()
    TravelHelperTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = { TravelHelperTopBar(navigationType = TopAppBarNavigationType.HOME) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F7FC))
                    .verticalScroll(scrollState)
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                SearchBar(onSearchClick)
                PopularityRanking()
                NearbyTravelDestination()
                ExchangeRate()
                Spacer(modifier = Modifier.height(165.dp))
            }
        }
    }
}

@Composable
private fun SearchBar(
    onSearchClick: () -> Unit
) {
    var value by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)

    ) {
        val shape = RoundedCornerShape(10.dp)
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "Find Your Destination",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(7.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
                .clip(shape)
                .background(Color.White)
                .clickable(
                    onClick = onSearchClick
                )
                .border(
                    width = 1.dp,
                    color = Color(0xFF949190),
                    shape = shape
                ),
            contentAlignment = Alignment.CenterStart
        ){
            Text(
                text = "Everything for your Travel",
                modifier = Modifier.padding(start = 10.dp)
            )

        }
    }
}

@Composable
private fun PopularityRanking() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        Text(
            text = "Popularity Ranking",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(7.dp))
        LazyRow {
            items(5) {
                PopularityContent()
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

@Composable
private fun NearbyTravelDestination() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        Text(
            text = "Nearby Destinations",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(7.dp))
        LazyRow {
            items(5) {
                PopularityContent()
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}

@Composable
private fun ExchangeRate() {
    Text(
        text = "Exchange Rate"
    )
}

@Preview
@Composable
private fun Test() {
    TravelHelperTheme {
        HomeScreen(
            navController = rememberNavController(),
            onSearchClick = {}
        )
    }
}
