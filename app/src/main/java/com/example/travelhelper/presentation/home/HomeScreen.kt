package com.example.travelhelper.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.travelhelper.R
import com.example.travelhelper.ui.component.CurrencyConverter
import com.example.travelhelper.ui.component.Loading
import com.example.travelhelper.ui.component.NearbyContent
import com.example.travelhelper.ui.component.PopularityContent
import com.example.travelhelper.ui.component.TopAppBarNavigationType
import com.example.travelhelper.ui.component.TravelHelperTopBar
import com.example.travelhelper.ui.theme.TravelHelperTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onSearchClick: () -> Unit,
    onDetailClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val nearbyDestinationUiState by viewModel.nearbyDestinationUiState.collectAsState()
    val popularDestinationUiState by viewModel.popularDestinationUiState.collectAsState()
    val currencyUiState by viewModel.currencyUiState.collectAsState()

    val focusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchNearbyDestinations("127.0775", "37.6317", "20000", "en")
        viewModel.fetchPopularDestinations("20210513", "20210513")
        viewModel.fetchCurrencyData()
    }

    val scrollState = rememberScrollState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = { focusManager.clearFocus() },
                indication = null,
                interactionSource = interactionSource
            ),
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
            PopularityRanking(popularDestinationUiState)
            NearbyTravelDestination(onDetailClick, nearbyDestinationUiState)
            ExchangeRate(currencyUiState)
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}

@Composable
private fun SearchBar(
    onSearchClick: () -> Unit
) {
    val shape = RoundedCornerShape(10.dp)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "Find Your Destination",
            style = MaterialTheme.typography.titleMedium
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
            contentAlignment = Alignment.CenterStart,
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Everything for your Travel",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null,
                )
            }
        }
    }
}

@Composable
private fun PopularityRanking(uiState: PopularDestinationUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        Text(
            text = "Popular Destination",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(7.dp))
        when(uiState) {
            is PopularDestinationUiState.Loading -> {
                Loading(modifier = Modifier.align(CenterHorizontally))
            }
            is PopularDestinationUiState.Empty -> {

            }
            is PopularDestinationUiState.PopularDestinations -> {
                LazyRow {
                    items(uiState.popularDestination.size) {
                        PopularityContent(item = uiState.popularDestination[it], imageUrl = uiState.imageList[it][0])
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
        }
    }
}

@SuppressLint("LogNotTimber")
@Composable
private fun NearbyTravelDestination(
    onClick: (String) -> Unit,
    uiState: NearbyDestinationUiState,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        Text(
            text = "Nearby Destinations",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(7.dp))
        when(uiState) {
            is NearbyDestinationUiState.Loading -> {
                Loading(modifier = Modifier.align(CenterHorizontally))
            }
            is NearbyDestinationUiState.Empty -> {
                Text("There are no tourist attractions nearby.")
            }
            is NearbyDestinationUiState.NearbyDestinations -> {
                LazyRow {
                    items(uiState.nearbyDestinations.size) {
                        Log.d("tsew", uiState.nearbyDestinations.size.toString())
                        Log.d("tsew", uiState.imageList.toString())
                        val imageUrl = if (uiState.imageList[it].isEmpty()) "" else uiState.imageList[it][0]
                        NearbyContent(uiState.nearbyDestinations[it], imageUrl, onClick)
                        Spacer(modifier = Modifier.width(20.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ExchangeRate(currencyUiState: CurrencyUiState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
    ) {
        Text(
            text = "Currency Converter",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(7.dp))
        CurrencyConverter(currencyUiState)
    }
}

@Preview
@Composable
private fun Test() {
    TravelHelperTheme {
        HomeScreen(
            onSearchClick = {},
            onDetailClick = {}
        )
    }
}
