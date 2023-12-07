package com.example.travelhelper.ui.main

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.travelhelper.ui.bookmark.BookmarkScreen
import com.example.travelhelper.ui.designsystem.component.BottomNavigationBar
import com.example.travelhelper.ui.home.HomeScreen
import com.example.travelhelper.ui.navigation.BottomTab
import com.example.travelhelper.ui.navigation.MainRoute
import com.example.travelhelper.ui.navigation.navigateSearch
import com.example.travelhelper.ui.search.SearchScreen
import com.example.travelhelper.ui.vision.VisionScreen
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val showBottomBar = remember { mutableStateOf(true) }

    DisposableEffect(navController) {
        val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
            showBottomBar.value = destination.route !in listOf(MainRoute.search, MainRoute.vision)
        }
        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }
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
            HomeScreen(
                navController = navController,
                onSearchClick = { navController.navigateSearch() }
            )
        }

        composable(BottomTab.Profile.route) {
            BookmarkScreen(navController)
        }

        composable(MainRoute.search) {
            SearchScreen(
                onBackClick = navController::popBackStack
            )
        }

        composable(
            route = "${MainRoute.vision}/{imageUri}",
            arguments = listOf(navArgument("imageUri") { type = NavType.StringType })
        ) { backStackEntry ->
            val imageUri = backStackEntry.arguments?.getString("imageUri")
            imageUri?.let { VisionScreen(
                URLDecoder.decode(it, StandardCharsets.UTF_8.toString()),
                onBackClick = navController::popBackStack
            ) }
        }
    }
}