package com.example.travelhelper.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.travelhelper.R
import com.example.travelhelper.ui.theme.TravelHelperTheme

enum class TopAppBarNavigationType { BACK, HOME, BOOKMARK, DETAIL }

@Composable
fun TravelHelperTopBar(
    navigationType: TopAppBarNavigationType,
    onNavigationClick: () -> Unit = {},
    onSaveClick: () -> Unit = {},
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(Color.White)
    ) {
        when(navigationType) {
            TopAppBarNavigationType.BACK -> {
                IconButton(onClick = onNavigationClick, modifier = Modifier.size(48.dp)) {
                    Icon(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 12.dp, horizontal = 12.dp),
                        painter = painterResource(id = R.drawable.ic_left),
                        contentDescription = null
                    )
                }
            }
            TopAppBarNavigationType.DETAIL -> {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(onClick = onNavigationClick, modifier = Modifier.size(48.dp)) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 12.dp, horizontal = 12.dp),
                            painter = painterResource(id = R.drawable.ic_left),
                            contentDescription = null
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    IconButton(onClick = onSaveClick, modifier = Modifier.size(48.dp)) {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(vertical = 12.dp, horizontal = 12.dp),
                            painter = painterResource(id = R.drawable.ic_bookmark),
                            tint = MaterialTheme.colorScheme.primary,
                            contentDescription = null
                        )
                    }
                }
            }
            TopAppBarNavigationType.BOOKMARK -> {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Bookmark",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 10.dp)
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(onClick = { onNavigationClick() }) {
                        Text("Clear All")
                    }
                }
            }
            TopAppBarNavigationType.HOME -> {
                Text(
                    text = "Home",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun TravelHelperTopAppBarTest() {
    TravelHelperTheme {
        TravelHelperTopBar(
            navigationType = TopAppBarNavigationType.DETAIL
        )
    }
}