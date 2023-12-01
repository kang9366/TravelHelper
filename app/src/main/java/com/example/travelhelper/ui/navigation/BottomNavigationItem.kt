package com.example.travelhelper.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.travelhelper.R

sealed class BottomTab(val route : String) {
    object Home : BottomTab("home_route")
    object Profile : BottomTab("profile_route")
}

data class BottomNavigationItem(
    val label : String = "",
    val icon : Painter? = null,
    val route : String = ""
) {
    @Composable
    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = painterResource(id = R.drawable.ic_home),
                route = BottomTab.Home.route
            ),
            BottomNavigationItem(
                label = "Bookmark",
                icon = painterResource(id = R.drawable.ic_bookmark),
                route = BottomTab.Profile.route
            ),
        )
    }
}
