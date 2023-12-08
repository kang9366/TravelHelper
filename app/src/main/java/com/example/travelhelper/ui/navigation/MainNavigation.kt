package com.example.travelhelper.ui.navigation

import androidx.navigation.NavController

fun NavController.navigateSearch() {
    navigate(MainRoute.search)
}

fun NavController.navigateVision(imageUri: String) {
    navigate("${MainRoute.vision}/$imageUri")
}

fun NavController.navigateDetail(title: String) {
    navigate("${MainRoute.detail}/$title")
}

object MainRoute {
    const val search = "search"
    const val vision = "vision"
    const val detail = "detail"
}