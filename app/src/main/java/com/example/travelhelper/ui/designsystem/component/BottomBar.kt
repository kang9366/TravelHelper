package com.example.travelhelper.ui.designsystem.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.travelhelper.R
import com.example.travelhelper.ui.designsystem.theme.Orange20
import com.example.travelhelper.ui.designsystem.theme.TravelHelperTheme
import com.example.travelhelper.ui.navigation.BottomNavigationItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = BottomNavigationItem().bottomNavigationItems()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val selectedTabIndex = items.indexOfFirst { it.route == currentRoute }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        NavigationBar(
            containerColor = Color.Transparent,
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp, 25.dp, 0.dp, 0.dp))
                .background(Color.White)
        ) {
            items.forEachIndexed { index, navigationItem ->
                val isSelected = index == selectedTabIndex
                NavigationBarItem(
                    modifier = Modifier.background(Color.White),
                    selected = isSelected,
                    label = {
                        Text(
                            text = navigationItem.label,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },
                    icon = {
                        Icon(
                            painter = navigationItem.icon!!,
                            contentDescription = navigationItem.label,
                            tint = if(isSelected) MaterialTheme.colorScheme.primary else Orange20
                        )
                    },
                    alwaysShowLabel = false,
                    onClick = {
                        if (navController.currentDestination?.route != navigationItem.route) {
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    }
                )
            }
        }
        FloatingActionButton(
            shape = CircleShape,
            onClick = {

            },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-30).dp)
                .background(Color.Transparent)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = "camera icon",
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview
@Composable
private fun Test() {
    val navigator = rememberNavController()
    TravelHelperTheme {
        BottomNavigationBar(navigator)
    }
}