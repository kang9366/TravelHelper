package com.example.travelhelper.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelhelper.R
import com.example.travelhelper.ui.designsystem.component.TopAppBarNavigationType
import com.example.travelhelper.ui.designsystem.component.TravelHelperTopBar
import com.example.travelhelper.ui.designsystem.theme.Gray
import com.example.travelhelper.ui.designsystem.theme.TravelHelperTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    onSearchClick: () -> Unit
) {
    TravelHelperTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            topBar = { TravelHelperTopBar(navigationType = TopAppBarNavigationType.HOME) }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF5F5F5)),
            ) {
                Spacer(modifier = Modifier.height(48.dp))
                SearchBar(onSearchClick)
                Text(text = "Popularity Ranking")
                NearbyTravelDestination()
                ExchangeRate()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchBar(
    onSearchClick: () -> Unit
) {
    var value by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(start = 20.dp, top = 20.dp),
            text = "Find Your Destination",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(7.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .height(44.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .clickable(
                    onClick = onSearchClick
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
private fun NearbyTravelDestination() {
    Text(
        text = "Nearby travel destinations"
    )
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
