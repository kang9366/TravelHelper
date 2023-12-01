package com.example.travelhelper

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.travelhelper.ui.bookmark.BookmarkScreen
import com.example.travelhelper.ui.designsystem.component.BottomNavigationBar
import com.example.travelhelper.ui.home.HomeScreen
import com.example.travelhelper.ui.navigation.BottomTab
import com.example.travelhelper.ui.navigation.MainRoute
import com.example.travelhelper.ui.navigation.navigateSearch
import com.example.travelhelper.ui.search.SearchScreen

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val showBottomBar = remember { mutableStateOf(true) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        showBottomBar.value = destination.route != MainRoute.search
    }

    Scaffold(
        bottomBar = { if (showBottomBar.value) BottomNavigationBar(navController) },
        content = {
            Navigation(navController)
        }
    )
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomTab.Home.route
    ) {
        composable(BottomTab.Home.route) {
            HomeScreen(navController) { navController.navigateSearch() }
        }

        composable(BottomTab.Profile.route) {
            BookmarkScreen(navController)
        }

        composable(MainRoute.search) {
            SearchScreen(navController = navController)
        }
    }
}