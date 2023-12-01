package com.example.travelhelper.ui.navigation

import androidx.navigation.NavController

fun NavController.navigateSearch() {
    navigate(MainRoute.search)
}

object MainRoute {
    const val search = "search"
    const val detail = "detail"
}