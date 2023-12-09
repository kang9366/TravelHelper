package com.example.travelhelper.presentation.search

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.travelhelper.ui.component.TopAppBarNavigationType
import com.example.travelhelper.ui.component.TravelHelperTopBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SearchScreen(
    onBackClick: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TravelHelperTopBar(
                navigationType = TopAppBarNavigationType.BACK,
                onNavigationClick = onBackClick
            )
        }
    ) {
        var queryString by remember {
            mutableStateOf("")
        }

        // if the search bar is active or not
        var isActive by remember {
            mutableStateOf(false)
        }

        val contextForToast = LocalContext.current.applicationContext

        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if (isActive) 0.dp else 8.dp),
            colors = SearchBarDefaults.colors(
                containerColor = Color.White,
            ),
            query = queryString,
            onQueryChange = { newQueryString ->
                queryString = newQueryString
            },
            onSearch = {
                isActive = false
                Toast.makeText(contextForToast, "Your query string: $queryString", Toast.LENGTH_SHORT)
                    .show()
            },
            active = isActive,
            onActiveChange = { activeChange ->
                isActive = activeChange
            },
            placeholder = {
                Text(text = "Search")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = null)
            },
        ) {

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchBar() {
    var queryString by remember {
        mutableStateOf("")
    }

    // if the search bar is active or not
    var isActive = true

    val contextForToast = LocalContext.current.applicationContext

    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = if (isActive) 0.dp else 8.dp),
        colors = SearchBarDefaults.colors(
            containerColor = Color.White,
        ),
        query = queryString,
        onQueryChange = { newQueryString ->
            queryString = newQueryString
        },
        onSearch = {
            isActive = false
            Toast.makeText(contextForToast, "Your query string: $queryString", Toast.LENGTH_SHORT)
                .show()
        },
        active = isActive,
        onActiveChange = { activeChange ->
            isActive = activeChange
        },
        placeholder = {
            Text(text = "Search")
        },
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null)
        },
    ) {

    }
}